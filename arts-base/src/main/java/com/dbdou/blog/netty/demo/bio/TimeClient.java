package com.dbdou.blog.netty.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by dentalulcer
 */
public class TimeClient {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Socket socket = new Socket("127.0.0.1", port);
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("QUERY TIME ORDER");
            System.out.println("send order 2 server succeed.");
            String resp = in.readLine();
            System.out.println("now is : " + resp);
        } finally {
            socket.close();
            socket = null;
        }

    }

}
