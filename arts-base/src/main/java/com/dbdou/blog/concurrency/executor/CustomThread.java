package com.dbdou.blog.concurrency.executor;

/**
 * Created by dentalulcer
 */
public class CustomThread implements Runnable {

    private int i;

    public CustomThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i);
    }
}
