package com.dbdou.arts.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("server.xml");
    }

}
