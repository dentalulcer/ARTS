package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 08/03/2019.
 */
public class DemoThread04 {

    private String name = "张三";
    private String address = "大兴";

    public synchronized void setVal(String name, String address) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.address = address;
        System.out.println("setVal结果：name=" + name + ", address=" + address);
    }

    public /*synchronized*/ void getVal() {
        System.out.println("getVal结果：name=" + name + ", address=" + address);
    }

    public static void main(String[] args) {

        final DemoThread04 demoThread04 = new DemoThread04();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                demoThread04.setVal("李四", "昌平");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                demoThread04.getVal();
            }
        });

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }

}
