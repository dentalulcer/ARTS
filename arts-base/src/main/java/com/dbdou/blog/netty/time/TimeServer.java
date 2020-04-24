package com.dbdou.blog.netty.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {

    public static void main(String[] args) throws InterruptedException {

        // boss 线程组，只处理连接请求 (默认cpu核数*2个线程)
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker 线程组，和客户端的业务处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 服务端启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap
                    .group(bossGroup, workerGroup) // 设置两个线程组
                    .channel(NioServerSocketChannel.class) // 服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeServerHandler());
                        }
                    }); // 给 workerGroup 的 EventLoop 对应的管道设置处理器

            System.out.println("server ready！");
            // 绑定一个端口并且同步，生成了一个 ChannelFuture 对象
            ChannelFuture future = bootstrap.bind(6667).sync();
            // 对关闭通道进行监听
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
