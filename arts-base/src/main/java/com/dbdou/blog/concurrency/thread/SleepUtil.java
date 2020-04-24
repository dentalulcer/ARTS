package com.dbdou.blog.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by dentalulcer .
 */
public class SleepUtil {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
