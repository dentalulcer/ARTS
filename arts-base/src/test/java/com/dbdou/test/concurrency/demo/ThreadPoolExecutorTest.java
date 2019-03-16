package com.dbdou.test.concurrency.demo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dentalulcer on 10/03/2019.
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask(i);
            executor.submit(task);
        }

    }

}

class MyTask implements Runnable {

    int index = 0;

    public MyTask(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ">>" + index);
    }
}

