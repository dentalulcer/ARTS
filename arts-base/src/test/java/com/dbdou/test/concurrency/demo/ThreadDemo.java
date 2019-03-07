package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 07/03/2019.
 */
public class ThreadDemo implements Runnable {

    private String name;
    private int age;
    BeanInterface bean;

    public ThreadDemo(String name, int age, BeanInterface bean) {
        this.name = name;
        this.age = age;
        this.bean = bean;
    }

    public void run() {
        bean.setInfo(name, age);
    }

}
