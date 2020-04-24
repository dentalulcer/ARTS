package com.dbdou.spring.boot.demo.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dentalulcer
 */
@EnableAdminServer
@SpringBootApplication
public class SpringBootDemoAdminServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootDemoAdminServerApplication.class, args);

    }

}
