package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 08/03/2019.
 */
public class DemoThread12 {

    public synchronized void run1() {
        System.out.println("enter run1");
//        this.notify();
        this.notifyAll();
        System.out.println("run1 finish, notify");
    }

    public synchronized void run2() {
        try {
            System.out.println("enter run2 and wait");
            this.wait();
            System.out.println("run2 finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void run3() {
        try {
            System.out.println("enter run3 and wait");
            this.wait();
            System.out.println("run3 finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        final DemoThread12 demoThread12 = new DemoThread12();

        new Thread(new Runnable() {
            public void run() {
                demoThread12.run2();
            }
        }, "t2").start();

        new Thread(new Runnable() {
            public void run() {
                demoThread12.run3();
            }
        }, "t3").start();

        new Thread(new Runnable() {
            public void run() {
                demoThread12.run1();
            }
        }, "t1").start();

    }


}
