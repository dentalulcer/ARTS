package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dentalulcer.
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            es.execute(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + ">>" + index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

}
