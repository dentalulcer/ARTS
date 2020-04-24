package com.dbdou.blog.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ByteBufDemo {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.buffer(3);

        for (int i = 0; i < 4; i++) {
            buf.writeByte(i);
        }

        while (buf.isReadable()) {
            System.out.println(buf.readByte());
        }

        ByteBuf buf2 = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
        byte[] array = buf2.array();
        System.out.println(new String(array, CharsetUtil.UTF_8));

    }

}
