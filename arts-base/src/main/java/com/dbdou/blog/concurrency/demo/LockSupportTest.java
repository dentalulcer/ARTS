package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start run");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " stop run");
        }, "thread1");
        thread.start();

        Thread.sleep(2000);

        LockSupport.unpark(thread);

    }

}
