package com.dbdou.blog.concurrency.demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new SportsMan("tom", barrier));
        executorService.execute(new SportsMan("jack", barrier));
        executorService.execute(new SportsMan("white", barrier));

        executorService.shutdown();

    }

    private static class SportsMan implements Runnable {
        private String name;
        private CyclicBarrier barrier;

        SportsMan(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(8) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("选手" + name + "准备好了");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("选手" + name + "起跑");
        }
    }

}
