package com.dbdou.blog.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {

        IntBuffer buf = IntBuffer.allocate(5);

        for (int i = 0; i < buf.capacity(); i++) {
            buf.put(i * 2);
        }

        buf.flip();

        System.out.println(buf.position());
        System.out.println(buf.position(2));
        System.out.println(buf.get(4));

//        while (buf.hasRemaining()) {
//            System.out.println(buf.get());
//        }
    }

}
