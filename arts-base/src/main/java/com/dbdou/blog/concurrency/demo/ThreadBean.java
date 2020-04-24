package com.dbdou.blog.concurrency.demo;

/**
 * Created by dentalulcer.
 */
public class ThreadBean implements BeanInterface {

    private String name;
    private int age;

    public /*synchronized*/ void setInfo(String name, int age) {
        this.name = name;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.age = age;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + " ThreadBean:" +
                hashCode() + "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
