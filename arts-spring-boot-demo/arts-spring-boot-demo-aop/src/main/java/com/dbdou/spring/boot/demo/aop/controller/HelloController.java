package com.dbdou.spring.boot.demo.aop.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dentalulcer
 */
@RestController
public class HelloController {

    @GetMapping("/say")
    public String say(@RequestParam("name") String name) {
        return StrUtil.format("hello {}", name);
    }

}
