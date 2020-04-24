package com.dbdou.blog.netty.rpc.netty;

import com.dbdou.blog.netty.rpc.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RpcClient {

    private String host;
    private int port;

    private static ExecutorService executorService = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    private static RpcClientHandler handler;

    public Object getBean(final Class<?> clazz) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{clazz}, (proxy, method, args) -> {
                    if (handler == null) {
                        initClient();
                    }
                    handler.setParam((String) args[0]);
                    return executorService.submit(handler).get();
                });
    }

    private void initClient() throws InterruptedException {
        handler = new RpcClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(handler);
                    }
                });

        bootstrap.connect(host, port).sync();

    }

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {

        HelloService helloService = (HelloService) new RpcClient("127.0.0.1", 6669).getBean(HelloService.class);
        System.out.println(helloService.hello("========"));

    }

}
