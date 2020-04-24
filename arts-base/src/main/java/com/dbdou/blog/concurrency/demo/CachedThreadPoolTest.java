package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dentalulcer.
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Runnable task = () -> {
                try {
                    Thread.sleep(index * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ">>" + index);
            };
            es.execute(task);
        }

    }

}

class Task implements Runnable {
    @Override
    public void run() {

    }
}

class Caller implements Callable<String> {
    @Override
    public String call() throws Exception {
        return null;
    }
}

