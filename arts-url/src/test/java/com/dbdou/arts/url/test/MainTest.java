package com.dbdou.arts.url.test;

import com.dbdou.arts.url.util.HashUtil;

/**
 * main test
 */
public class MainTest {

    public static void main(String[] args) {

        testNum2Hex();

    }

    public static void testNum2Hex() {
        System.out.println(HashUtil.num2Hex(1));
        System.out.println(HashUtil.num2Hex(1000000000));
        System.out.println(HashUtil.num2Hex(2000000000));
    }

}
