package com.dbdou.blog.concurrency.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dentalulcer.
 */
public class DemoThread16 {

    public static void main(String[] args) {

        long startTm = System.currentTimeMillis();
//        final List<Integer> list = new ArrayList<Integer>();
        final List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000000; i++) {
            es.execute(new Runnable() {
                public void run() {
                    list.add(1);
                }
            });
        }
        es.shutdown();
        while (true) {
            if (es.isTerminated()) {
                System.out.println(list.size() + "-" + (System.currentTimeMillis()-startTm));
                break;
            }
        }

    }

}
