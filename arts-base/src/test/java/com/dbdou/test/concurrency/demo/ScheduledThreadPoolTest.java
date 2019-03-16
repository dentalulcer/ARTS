package com.dbdou.test.concurrency.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by dentalulcer on 10/03/2019.
 */
public class ScheduledThreadPoolTest {

    public static void test1() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + ">> delay " + index + " seconds run");
            };
            es.schedule(task, i, TimeUnit.SECONDS);
        }

        es.shutdown();
    }

    public static void test2() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(12);
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + ">> sleep " + System.currentTimeMillis());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ">> run " + System.currentTimeMillis());
        };
        es.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
//        es.shutdown();
    }

    public static void test3() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(10);
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + ">> sleep " + System.currentTimeMillis());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ">> run " + System.currentTimeMillis());
        };
        ScheduledFuture<?> scheduledFuture = es.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledFuture.cancel(true);
        es.shutdown();
    }

    public static void main(String[] args) {

//        test1();
//        test2();
        test3();
    }

}
