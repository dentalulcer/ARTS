package com.dbdou.test.concurrency.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dentalulcer on 09/03/2019.
 */
public class DemoThread17 {

    public static void main(String[] args) {

        final Map<String, Integer> map = new ConcurrentHashMap<>();
//        final Map<String, Integer> map = new Hashtable<String, Integer>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                long start = System.currentTimeMillis();
                for (int j = 0; j < 1000000; j++) {
                    map.put("k" + j, j);
                }
                System.out.println(System.currentTimeMillis() - start);
            }).start();
        }

    }

}
