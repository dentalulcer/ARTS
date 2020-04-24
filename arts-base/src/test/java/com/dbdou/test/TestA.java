package com.dbdou.test;

import cn.hutool.core.util.StrUtil;

/**
 * Created by dentalulcer
 */
public class TestA {

    private String abc;

    private static final String elements = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        // 1. 初始化发号器，从db读取，本地存储


        // 2. 发号

        // 3. 重新取号

    }

    private static void testEnDeCode() {
        System.out.println(encode(1));
        System.out.println(encode(100));
        System.out.println(encode(100000));
        System.out.println(encode(1000000000));
        System.out.println(encode(10000000000L));
        System.out.println(encode(61));
        System.out.println(encode(62));
        System.out.println(encode(63));
        System.out.println(encode(123));
        System.out.println(encode(124));
        System.out.println(encode(125));

        System.out.println(decode("000001"));
        System.out.println(decode("00001c"));
        System.out.println(decode("000Q0u"));
        System.out.println(decode("15ftgG"));
        System.out.println(decode("Aukyoa"));
        System.out.println(decode("00000z"));
        System.out.println(decode("000010"));
        System.out.println(decode("000011"));
        System.out.println(decode("00001z"));
        System.out.println(decode("000020"));
        System.out.println(decode("000021"));
    }

    private static String encode(long id) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(elements.charAt((int) (id % 62)));
            id = id / 62;
        } while (id > 0);
        return StrUtil.fillBefore(StrUtil.reverse(sb.toString()), '0', 6);
    }

    private static long decode(String str) {
        long id = 0L;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            id += elements.indexOf(chars[i]) * Math.pow(62, (5 - i));
        }
        return id;
    }


}
