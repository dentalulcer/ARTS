package com.dbdou.blog.concurrency.demo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "尝试获取锁");
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + "获取到了锁=====");
                    Thread.sleep(new Random().nextInt(3)*1000);

                    System.out.println(Thread.currentThread().getName() + "释放了锁-----");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread"+i));
        }

        executorService.shutdown();
    }

}
