package com.dbdou.test.concurrency.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by dentalulcer on 2019/3/16.
 */
public class MultiThread {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo tf : threadInfos) {
            System.out.println("[" + tf.getThreadId() + "] " + tf.getThreadName());
        }
    }

}
