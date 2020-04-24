package com.dbdou.blog.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupChatClient {

    private String name;

    private Selector selector;

    private SocketChannel socketChannel;

    private static final int PORT = 6666;
    private static final String HOST = "127.0.0.1";

    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        name = socketChannel.getLocalAddress().toString().substring(1);
    }

    public void send(String msg) throws IOException {
        msg = name + "说：" + msg;
        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

    public void read() throws IOException {
        int count = selector.select();
        if (count > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    socketChannel.read(buf);
                    String msg = new String(buf.array());
                    System.out.println(msg.trim());
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient client = new GroupChatClient();
        new Thread(() -> {
            while (true) {
                try {
                    client.read();
                    Thread.sleep(3000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            client.send(scanner.nextLine());
        }
    }

}
