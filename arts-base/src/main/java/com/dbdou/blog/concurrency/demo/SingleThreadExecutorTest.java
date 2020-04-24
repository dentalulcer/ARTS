package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dentalulcer.
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args) {

        ExecutorService es = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = 1;
            Runnable task = () -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ">>" + index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            es.execute(task);
        }
        es.shutdown();
    }

}
