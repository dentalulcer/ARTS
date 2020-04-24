package com.dbdou.arts.rpc.client;

import com.dbdou.arts.rpc.common.bean.DouRequest;
import com.dbdou.arts.rpc.common.bean.DouResponse;
import com.dbdou.arts.rpc.common.codec.DouDecoder;
import com.dbdou.arts.rpc.common.codec.DouEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DouClient extends SimpleChannelInboundHandler<DouResponse> {

    private final String host;
    private final int port;

    private DouResponse response;

    public DouClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DouResponse douResponse) throws Exception {
        this.response = douResponse;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public DouResponse send(DouRequest request) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new DouEncoder(DouRequest.class)); // 编码 RPC 请求
                    pipeline.addLast(new DouDecoder(DouResponse.class)); // 解码 RPC 响应
                    pipeline.addLast(DouClient.this); // 处理 RPC 响应
                }
            });
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush(request).sync();
            channel.closeFuture().sync();
            return response;
        } finally {
            group.shutdownGracefully();
        }
    }
}
