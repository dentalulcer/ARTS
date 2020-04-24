package com.dbdou.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestBus {

    private static final int CAP = 5;
    // 阻塞队列
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(CAP);
    private AtomicInteger num = new AtomicInteger();

    public void produce() throws InterruptedException {
        while (true) {
            boolean flag = blockingQueue.offer(num.incrementAndGet(), 1, TimeUnit.SECONDS);
            System.out.println("produce result:" + flag);
            Thread.sleep(1000);
        }
    }


    public void consume() throws InterruptedException {
        while (true) {
            Integer ele = blockingQueue.poll(1, TimeUnit.SECONDS);
            if (ele == null) {
                return;
            }
            System.out.println("consume ele:" + ele);
            Thread.sleep(2000);
        }
    }

    public static void main(String[] args) {
        TestBus demo = new TestBus();

        ExecutorService proService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            proService.execute(() -> {
                try {
                    demo.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        ExecutorService conService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            conService.execute(() -> {
                try {
                    demo.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
