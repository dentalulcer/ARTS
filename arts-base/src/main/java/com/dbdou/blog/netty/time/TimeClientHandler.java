package com.dbdou.blog.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪时触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client ctx :" + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        System.out.println("server address: " + ctx.channel().remoteAddress() +
                ", reply: " + buf.toString(CharsetUtil.UTF_8));
    }
}
