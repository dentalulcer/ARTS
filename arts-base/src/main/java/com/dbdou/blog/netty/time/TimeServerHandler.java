package com.dbdou.blog.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义一个 Handler，需要继承 Netty 规定好的 HandlerAdapter
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送对数据
     *
     * @param ctx 上下文对象，包含 pipeline, channel 等
     * @param msg 客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server context: " + ctx);
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client address: " + ctx.channel().remoteAddress()
                + ", msg: " + buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
