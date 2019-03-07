package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 08/03/2019.
 */
public class DemoThread09 {

    private String lock = "lock handler";

    private void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                lock = "change lock handler";
                Thread.sleep(3000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final DemoThread09 demoThread09 = new DemoThread09();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                demoThread09.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                demoThread09.method();
            }
        }, "t2");

        t1.start();
        t2.start();

    }

}
