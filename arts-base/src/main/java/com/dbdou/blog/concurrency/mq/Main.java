package com.dbdou.blog.concurrency.mq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        Provider pro1 = new Provider("pro1", queue);
        Provider pro2 = new Provider("pro2", queue);
        Provider pro3 = new Provider("pro3", queue);
        Consumer con1 = new Consumer("con1", queue);
        Consumer con2 = new Consumer("con2", queue);
        Consumer con3 = new Consumer("con3", queue);

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.execute(pro1);
        executorService.execute(pro2);
        executorService.execute(pro3);
        executorService.execute(con3);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pro1.stop();
        pro2.stop();
        pro3.stop();
        executorService.shutdown();

    }

}
