package com.dbdou.test.concurrency.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dentalulcer on 08/03/2019.
 */
public class DemoThread11 {

    private Object lock = new Object();
    private List<Integer> list = new ArrayList<Integer>();

    public void put() {
        synchronized (lock) {
            int i=0;
            while (i++ < 10) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ">put: " + i);
                list.add(i);
                if (i == 5) {
                    lock.notify();
                    System.out.println(Thread.currentThread().getName() + ">发送通知，唤醒正在wait的锁");
                }
            }
        }
    }

    public void get() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + ">获得锁");
                System.out.println(Thread.currentThread().getName() + ">等待，并让出锁");
                lock.wait();
                System.out.println(Thread.currentThread().getName() + ">被唤醒");
                for (int i : list) {
                    System.out.println(Thread.currentThread().getName() + ">get: " + i);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final DemoThread11 demoThread11 = new DemoThread11();

        new Thread(new Runnable() {
            public void run() {
                demoThread11.get();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            public void run() {
                demoThread11.put();
            }
        }, "t2").start();



    }

}
