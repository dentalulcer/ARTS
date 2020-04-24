package com.dbdou.spring.boot.demo.helloworld;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dentalulcer
 */
@RestController
@SpringBootApplication
public class SpringBootDemoHelloworldApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootDemoHelloworldApplication.class, args);

    }

    @GetMapping("/hello")
    public String say(@RequestParam(required = false, name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "world";
        }
        return StrUtil.format("hello {}", who);
    }

}
