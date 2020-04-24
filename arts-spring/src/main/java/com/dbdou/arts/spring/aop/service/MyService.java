package com.dbdou.arts.spring.aop.service;

import com.dbdou.arts.spring.aop.innotation.MyAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dentalulcer
 */
@Service
public class MyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @MyAnnotation
    public void say() {
        System.out.println("hello myService!");
    }

    @Transactional
    public void save() {
        jdbcTemplate.execute("insert into my_user(id, name) values (2, 'jack')");
        jdbcTemplate.execute("insert into my_log(id, user_id) values (1, 2)");
    }


}
