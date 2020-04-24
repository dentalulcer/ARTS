package com.dbdou.blog.netty.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class RpcClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext ctx;
    private String result;
    private String param;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------channelActive--------");
        this.ctx = ctx;
        System.out.println(this.ctx);
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("--------channelRead--------");
        result = msg.toString();
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public synchronized Object call() throws Exception {
        System.out.println("--------call--------");
        ctx.writeAndFlush(param);
        wait();
        return result;
    }

    public void setParam(String param) {
        System.out.println("--------setParam--------");
        this.param = param;
    }
}
