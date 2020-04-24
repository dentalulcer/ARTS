package com.dbdou.arts.rpc.server;

import com.dbdou.arts.rpc.common.bean.DouRequest;
import com.dbdou.arts.rpc.common.bean.DouResponse;
import com.dbdou.arts.rpc.common.codec.DouDecoder;
import com.dbdou.arts.rpc.common.codec.DouEncoder;
import com.dbdou.arts.rpc.registry.ZkServiceRegistry;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DouServer implements ApplicationContextAware, InitializingBean {

    private String serviceAddress;
    private ZkServiceRegistry registry;

    private Map<String, Object> handleMap = new HashMap<>();

    public DouServer(String serviceAddress, ZkServiceRegistry registry) {
        this.serviceAddress = serviceAddress;
        this.registry = registry;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(DouService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                DouService douService = serviceBean.getClass().getAnnotation(DouService.class);
                String serviceName = douService.value().getName();
                String serviceVersion = douService.version();
                if (!"".equals(serviceVersion)) {
                    serviceName += "-" + serviceVersion;
                }
                handleMap.put(serviceName, serviceBean);
            }
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建并初始化 Netty 服务端 Bootstrap 对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new DouDecoder(DouRequest.class)); // 解码 RPC 请求
                    pipeline.addLast(new DouEncoder(DouResponse.class)); // 编码 RPC 响应
                    pipeline.addLast(new DouServerHandler(handleMap)); // 处理 RPC 请求
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            // 获取 RPC 服务器的 IP 地址与端口号
            String[] addressArray = serviceAddress.split(":");
            String ip = addressArray[0];
            int port = Integer.parseInt(addressArray[1]);
            // 启动 RPC 服务器
            ChannelFuture future = bootstrap.bind(ip, port).sync();
            // 注册 RPC 服务地址
            if (registry != null) {
                for (String interfaceName : handleMap.keySet()) {
                    registry.registry(interfaceName, serviceAddress);
                    log.debug("register service: {} => {}", interfaceName, serviceAddress);
                }
            }
            log.debug("server started on port {}", port);
            // 关闭 RPC 服务器
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}
