package com.dbdou.blog.concurrency.demo;

/**
 * Created by dentalulcer
 */
public class DemoThread02 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(20 * 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread mainThread = Thread.currentThread();

        Thread t2 = new Thread(mainThread::interrupt);

        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getName() + " wait " + t1.getName() + " and " + t2.getName() + " run over!");

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final:" + t1.getName() + " and " + t2.getName() + " run over!");

        System.out.println("t1.state: " + t1.getState());
        System.out.println("t2.state: " + t2.getState());
        System.out.println("mainThread.state: " + mainThread.getState());

    }

}
