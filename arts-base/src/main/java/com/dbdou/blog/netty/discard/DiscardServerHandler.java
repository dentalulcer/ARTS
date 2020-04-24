package com.dbdou.blog.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*
        // 默默丢弃掉接收到的数据
        ((ByteBuf) msg).release();
         */

        /*
        // 打印接收到的数据
        ByteBuf inBuf = (ByteBuf) msg;
        System.out.println(inBuf.toString(io.netty.util.CharsetUtil.US_ASCII));
        try {
            while (inBuf.isReadable()) {
                System.out.print((char) inBuf.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
        */

        // 打印接收到的数据
        ByteBuf inBuf = (ByteBuf) msg;
        System.out.println(inBuf.toString(io.netty.util.CharsetUtil.US_ASCII));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
