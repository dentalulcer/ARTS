package com.dbdou.blog.netty.demo.nio;

/**
 * Created by dentalulcer
 */
public class TimeServer {

    public static void main(String[] args) {

        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        MultiplexerTimeServer server = new MultiplexerTimeServer(port);
        new Thread(server).start();
    }

}
