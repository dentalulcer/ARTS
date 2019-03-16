package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 09/03/2019.
 */
public class DemoThread15 {

    public static void main(String[] args) {

        final ThreadLocal<Integer> sum = new ThreadLocal<Integer>();

        new Thread(new Runnable() {
            public void run() {
                sum.set(100);
                System.out.println("t1 set sum=" + sum.get());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 get sum=" + sum.get());
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                System.out.println("t2 get sum=" + sum.get());
                sum.set(200);
                System.out.println("t2 get sum=" + sum.get());
            }
        }).start();

    }

}
