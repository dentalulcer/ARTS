package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 08/03/2019.
 */
public class DemoThread05 {

    public synchronized void run1() {
        System.out.println(Thread.currentThread().getName() + ">run1...");
        // 调用同类中的synchronized方法不会引起死锁，隐式重入锁
        run2();
    }

    public synchronized void run2() {
        System.out.println(Thread.currentThread().getName() + ">run2...");
    }

    public static void main(String[] args) {
        final DemoThread05 demoThread05 = new DemoThread05();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                demoThread05.run1();
            }
        });

        t1.start();
    }

}
