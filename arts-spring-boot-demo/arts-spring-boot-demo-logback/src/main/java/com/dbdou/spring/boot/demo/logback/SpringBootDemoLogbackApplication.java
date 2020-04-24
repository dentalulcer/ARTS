package com.dbdou.spring.boot.demo.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dentalulcer
 */
@Slf4j
@RestController
@SpringBootApplication
public class SpringBootDemoLogbackApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoLogbackApplication.class, args);
        int count = context.getBeanDefinitionCount();
        log.trace("Spring boot启动初始化了 {} 个 Bean", count);
        log.debug("Spring boot启动初始化了 {} 个 Bean", count);
        log.info("Spring boot启动初始化了 {} 个 Bean", count);
        log.warn("Spring boot启动初始化了 {} 个 Bean", count);
        log.error("Spring boot启动初始化了 {} 个 Bean", count);
        try {
            int i = 0;
            int j = 1 / i;
        } catch (Exception e) {
            log.error("【SpringBootDemoLogbackApplication】启动异常：", e);
        }

    }

    @GetMapping("/logback")
    public String logback() {

        log.info("logback info");

        return "logback info";
    }


}
