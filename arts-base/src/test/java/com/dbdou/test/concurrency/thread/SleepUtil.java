package com.dbdou.test.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by dentalulcer on 2019/3/16.
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
