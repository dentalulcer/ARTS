package com.dbdou.blog.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);
        SocketAddress address = new InetSocketAddress(6666);

        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作！");
            }
        }

        String str = "hello world";
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes());

        socketChannel.write(buf);

        System.in.read();

    }

}
