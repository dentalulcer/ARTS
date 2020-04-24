package com.dbdou.blog.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 群聊服务端
 * <p>
 * 1. 服务器启动并监听6666端口
 * 2. 服务器接收客户端请求，并实现转发 (处理上线、离线)
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6666;

    public GroupChatServer() throws IOException {
        selector = Selector.open();
        listenChannel = ServerSocketChannel.open();

        listenChannel.socket().bind(new InetSocketAddress(PORT));
        listenChannel.configureBlocking(false);

        listenChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        while (true) {
            int count = selector.select(2000);
            if (count == 0) {
                System.out.println("等待……");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    processAccept();
                }
                if (key.isReadable()) {
                    processRead(key);
                }
                iterator.remove();
            }
        }
    }

    private void processAccept() throws IOException {
        SocketChannel socketChannel = listenChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println(socketChannel.getRemoteAddress() + "上线");
    }

    private void processRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        SocketChannel socketChannel = listenChannel.accept();
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int readCount = channel.read(buf);
            if (readCount > 0) {
                String msg = new String(buf.array());
                System.out.println("from 客户端：" + msg);
                sendToOther(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendToOther(String msg, SocketChannel self) {
        System.out.println("转发中……");
        Set<SelectionKey> keys = selector.keys();
        keys.forEach((key) -> {
            SelectableChannel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buf = ByteBuffer.wrap(msg.getBytes());
                try {
                    dest.write(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
