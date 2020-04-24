package com.dbdou.arts.spring.bean;

/**
 * Created by dentalulcer
 */
public class Ex003Bean {

    private Ex002Bean ex002Bean;

    public void get002Bean() {

        System.out.println(ex002Bean);

    }

    public void setEx002Bean(Ex002Bean ex002Bean) {
        this.ex002Bean = ex002Bean;
    }
}
