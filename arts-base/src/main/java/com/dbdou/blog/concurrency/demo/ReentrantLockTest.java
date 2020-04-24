package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dentalulcer.
 */
public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock();

    public void run1() {
        lock.lock();
        System.out.println("run1 start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run1 end");
        lock.unlock();
    }

    public void run2() {
        lock.lock();
        System.out.println("run2 start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run2 end");
        lock.unlock();
    }

    public static void main(String[] args) {

        ReentrantLockTest test = new ReentrantLockTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.run1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.run2();
            }
        });

        t1.start();
        t2.start();

    }

}
