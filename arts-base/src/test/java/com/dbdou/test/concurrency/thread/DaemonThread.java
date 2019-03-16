package com.dbdou.test.concurrency.thread;

/**
 * Created by dentalulcer on 2019/3/16.
 */
public class DaemonThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();

    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtil.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
