package com.dbdou.blog.jvm.reference;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by dentalulcer
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {

//        test1();

//        test2();
//        test3();

    }

    private static void test1() {
        Object o1 = new Object();
        WeakReference<Object> wr1 = new WeakReference<Object>(o1);
        o1 = null;
        // 此时进行垃圾回收，无论内存是否充足，都会被回收
        System.gc();
        Object o2 = wr1.get();
        System.out.println(o2);
    }

    // -Xmx4M -XX:+PrintGCDetails
    private static void test2() {
        Map<String, Object> map = new WeakHashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put("key"+i, new byte[i]);
        }
    }

    // -Xmx4M -XX:+PrintGCDetails
    private static void test3() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put("key"+i, new byte[i]);
        }
    }

}
