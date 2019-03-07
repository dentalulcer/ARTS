package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 08/03/2019.
 */
public class DemoThread10 {

    public static void main(String[] args) {
        final Object a = new Object(), b = new Object();

        new Thread(new Runnable() {
            public void run() {
                synchronized (a) {
                    System.out.println("线程t1获得a的锁");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        System.out.println("线程t1获得b的锁");
                    }

                }
            }
        }, "t1").start();

        new Thread(new Runnable() {
            public void run() {
                synchronized (b) {
                    System.out.println("线程t2获得b的锁");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a) {
                        System.out.println("线程t2获得a的锁");
                    }

                }
            }
        }, "t2").start();

    }

}
