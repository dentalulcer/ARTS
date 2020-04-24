package com.dbdou.blog.concurrency.demo;

/**
 * Created by dentalulcer.
 */
public class DemoThread13 implements Runnable{

    public static volatile int sum = 0;

    public static void add() {
        System.out.println(Thread.currentThread().getName() + "初始化sum=" + sum);
        for (int i = 0; i < 10000; i++) {
            sum = sum + 1;
        }
        System.out.println(Thread.currentThread().getName() + "计算后sum=" + sum);
    }

    public void run() {
        add();
    }

    public static void main(String[] args) {

//        ExecutorService es = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            es.submit(new DemoThread13());
//        }
//        es.shutdown();
//
//        while (true) {
//            if (es.isTerminated()) {
//                System.out.println("sum最终=" + sum);
//                break;
//            }
//        }

        Thread t = new Thread(() -> {
            System.out.println(123);
        }, "t");

        t.start();
        t.start();

    }

}
