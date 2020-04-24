package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dentalulcer.
 */
public class DemoThread14 implements Runnable{

    public static /*volatile*/ AtomicInteger sum = new AtomicInteger();

    public static void add() {
        System.out.println(Thread.currentThread().getName() + "初始化sum=" + sum);
        for (int i = 0; i < 10000; i++) {
            sum.getAndAdd(1);
        }
        System.out.println(Thread.currentThread().getName() + "计算后sum=" + sum);
    }

    public void run() {
        add();
    }

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.submit(new DemoThread14());
        }
        es.shutdown();

        while (true) {
            if (es.isTerminated()) {
                System.out.println("sum最终=" + sum);
                break;
            }
        }

    }

}
