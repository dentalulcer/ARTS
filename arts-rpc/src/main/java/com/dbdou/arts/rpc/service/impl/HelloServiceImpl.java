package com.dbdou.arts.rpc.service.impl;

import com.dbdou.arts.rpc.server.DouService;
import com.dbdou.arts.rpc.service.HelloService;

@DouService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

}
