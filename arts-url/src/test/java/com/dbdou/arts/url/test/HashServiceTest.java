package com.dbdou.arts.url.test;

import com.dbdou.arts.url.service.HashService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * hashService unit test
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HashServiceTest {

    @Autowired
    private HashService hashService;

    @Test
    public void testCreate() {
        System.out.println(hashService.create("https://www.baidu.com"));
        System.out.println(hashService.create("https://www.baidu.com"));
    }

    @Test
    public void testForward() {
        System.out.println(hashService.forward("AAAAAA"));
        System.out.println(hashService.forward("1HoyUu"));
    }

}
