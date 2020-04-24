package com.dbdou.blog.concurrency.demo;

/**
 * Created by dentalulcer.
 */
public class SingletonBean implements BeanInterface {

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
        return "SingletonBean:" +
                hashCode() + "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private static SingletonBean bean;

    private SingletonBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static SingletonBean getBean() {
        if (bean != null) {
            return bean;
        }
        bean = new SingletonBean("tom", 123);
        return bean;
    }

}
