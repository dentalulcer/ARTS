package com.dbdou.arts.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dentalulcer
 */
@Component
public class Ex001Bean {

    @Autowired
    private Ex002Bean ex002Bean;

    public void get002Bean() {

        System.out.println(ex002Bean);

    }

}
