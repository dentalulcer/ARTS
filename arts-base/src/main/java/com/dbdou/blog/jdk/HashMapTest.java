package com.dbdou.blog.jdk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dentalulcer
 */
public class HashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(10));
        System.out.println(tableSizeFor(100));
        System.out.println(tableSizeFor(100000));

        Map<String, String> map = new HashMap<>();

        System.out.println(map.put("a", "a"));
        System.out.println(map.put("a", "aa"));

        // 使用 '&' 代替 '%' 进行取模运算，提升计算效率：((a-1) & b) == (b % a)
        System.out.println(15 & 1);
        System.out.println(1 % 16);
        System.out.println(15 & 20);
        System.out.println(20 % 16);
        System.out.println(15 & 21);
        System.out.println(21 % 16);
        System.out.println(15 & 30);
        System.out.println(30 % 16);



        System.out.println(hash(1));
        System.out.println(hash(20));
        System.out.println(hash(21));
        System.out.println(hash(30));

        System.out.println(1 >>> 16 );
        System.out.println(20 >>> 16 );


    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}
