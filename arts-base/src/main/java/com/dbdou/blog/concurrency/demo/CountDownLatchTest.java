package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) {

        System.out.println("main thread start");

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                System.out.println("t1 start");
                latch.await();
                System.out.println("t1 run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                System.out.println("t2 start");
                latch.await();
                System.out.println("t2 run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("latch val=" + latch);
            latch.countDown();
            System.out.println("latch val=" + latch);
            latch.countDown();
            System.out.println("latch val=" + latch);
        }).start();

        System.out.println("main thread end");

    }

}
