package com.dbdou.arts.rpc;

import com.dbdou.arts.rpc.client.DouProxy;
import com.dbdou.arts.rpc.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloClient {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("client.xml");
        DouProxy douProxy = context.getBean(DouProxy.class);
        HelloService helloService = douProxy.create(HelloService.class);
        String result = helloService.hello(" world");
        System.out.println(result);

        System.exit(0);

    }

}
