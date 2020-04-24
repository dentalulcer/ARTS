package com.dbdou.blog.concurrency.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dentalulcer
 */
public class CustomMain {

    public static void main(String[] args) {

        originThreadPoolTest();

        customThreadPoolTest();

    }

    private static void customThreadPoolTest() {

    }

    private static void originThreadPoolTest() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(3, 5,
                3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));

        for (int i = 0; i < 10; i++) {
            executorService.execute(new CustomThread(i));
        }

//        System.err.println("before sleep active count: " + executorService.getActiveCount());
//        System.err.println("before sleep: completed task count: " + executorService.getCompletedTaskCount());
//        System.err.println("before sleep: core pool size: " + executorService.getCorePoolSize());
//        System.err.println("before sleep: largest pool size: " + executorService.getLargestPoolSize());
//        System.err.println("before sleep: maximum pool size: " + executorService.getMaximumPoolSize());
        System.err.println("before sleep: pool size: " + executorService.getPoolSize());
//        System.err.println("before sleep: task count: " + executorService.getTaskCount());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.err.println("after sleep active count: " + executorService.getActiveCount());
//        System.err.println("after sleep: completed task count: " + executorService.getCompletedTaskCount());
//        System.err.println("after sleep: core pool size: " + executorService.getCorePoolSize());
//        System.err.println("after sleep: largest pool size: " + executorService.getLargestPoolSize());
//        System.err.println("after sleep: maximum pool size: " + executorService.getMaximumPoolSize());
        System.err.println("after sleep: pool size: " + executorService.getPoolSize());
//        System.err.println("after sleep: task count: " + executorService.getTaskCount());

        for (int i = 20; i < 23; i++) {
            executorService.execute(new CustomThread(i));
        }

        executorService.shutdown();
    }

}
