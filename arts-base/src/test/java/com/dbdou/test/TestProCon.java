package com.dbdou.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestProCon {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    private AtomicInteger num = new AtomicInteger(0);

    private boolean proFlag = true;
    private boolean conFlag = true;

    private void produce() throws InterruptedException {
        while (proFlag) {
            boolean flag = queue.offer(" produce: " + num.incrementAndGet(), 100, TimeUnit.MILLISECONDS);
            System.out.println(Thread.currentThread().getName() + "produce result:" + flag);
            if (!flag) {
                proFlag = false;
            }
            Thread.sleep(1000);
        }
    }

    private void consumer() throws InterruptedException {
        while (conFlag) {
            String msg = queue.poll(1, TimeUnit.MILLISECONDS);
            if (msg == null) {
                conFlag = false;
            }
            System.out.println("consume msg:" + msg);
            Thread.sleep(2000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService producerService = Executors.newFixedThreadPool(5);
        ExecutorService consumerService = Executors.newFixedThreadPool(3);

        TestProCon testProCon = new TestProCon();

        for (int i = 0; i < 5; i++) {
            producerService.execute(() -> {
                try {
                    testProCon.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 3; i++) {
            consumerService.execute(() -> {
                try {
                    testProCon.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

}
