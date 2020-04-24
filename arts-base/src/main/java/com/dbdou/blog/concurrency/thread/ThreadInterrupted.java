package com.dbdou.blog.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by dentalulcer .
 */
public class ThreadInterrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        Thread busyThread = new Thread(new BusyRunner(), "busyThread");

        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        SleepUtil.second(2);

    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtil.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

}
