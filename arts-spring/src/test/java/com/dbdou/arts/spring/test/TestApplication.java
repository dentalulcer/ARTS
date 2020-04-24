package com.dbdou.arts.spring.test;

import com.dbdou.arts.spring.aop.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dentalulcer
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    private MyService myService;

    @Test
    public void testSay() {
        myService.say();
    }

    @Test
    public void testSave() {
        myService.save();
    }

}
