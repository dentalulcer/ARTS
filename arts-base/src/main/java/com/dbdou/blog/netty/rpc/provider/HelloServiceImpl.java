package com.dbdou.blog.netty.rpc.provider;

import com.dbdou.blog.netty.rpc.service.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String msg) {
        System.out.println("receive from client msg: " + msg);
        return "server reply: " + msg;
    }

}
