package com.dbdou.blog.jdk;

import java.util.Locale;

/**
 *
 * Created by dentalulcer.
 */
public class StringTest {

    public static void main(String[] args) {

        String str = "abc";

        // testMethodChoice(new StringBuffer("1"));

        testCompareIgnoreCase();

        System.out.println(Locale.getDefault());

    }

    static void testCompareIgnoreCase() {
        String str1 = "a你好", str2 = "B再见";
        int i = str1.compareToIgnoreCase(str2);
        System.out.println(i);
    }

    static void test1(String str) {
        int i = str.codePointAt(0);
        System.out.println(i);
    }

    static void testMethodChoice(String a) {
        System.out.println(1);
    }

    static void testMethodChoice(CharSequence cs) {
        System.out.println(2);        
    }

}
