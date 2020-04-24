package com.dbdou.blog.jdk;

import java.util.Arrays;

/**
 *
 * Created by dentalulcer .
 */
public class StringUse {

    public static void main(String[] args) {

        /* ---------- constructor start ---------- */
        String a = "a";     // "a"
        String b = new String("b");     // "b"
        String c = new String(new char[]{'c'}); // "c"
        String d = new String(new char[]{'c','d','e'}, 1, 1); // "d"
        String e = new String(new int[]{100,101,102}, 1, 1);  // "e"
        String f = new String(new byte[]{101,102}, 1, 1);    // "f"
        String g = new String(new StringBuffer("g").append(" hello"));     // "g hello"
        String h = new String(new StringBuilder("h").append(" hello"));    // "h hello"
    
        System.out.println(Arrays.toString(new String[]{a, b, c, d, e, f, g, h}));
        /* ---------- constructor end ---------- */


        int i = a.length();     // 1
        char j = b.charAt(0);   // 'b'
        int k = c.codePointAt(0);       // 99
        int l = d.codePointBefore(1);   // 100
        int m = g.codePointCount(0, 2); // 2
        int n = h.offsetByCodePoints(1, 3); // 4

        System.out.println(Arrays.toString(new Object[]{i, j, k, l, m, n}));    


        char[] dst = new char[5];
        g.getChars(0, 3, dst, 1);
        for (char aDst : dst) {
            System.out.println("hello:" + aDst);
        }


        boolean o = g.equals("g hello");    // true
        boolean p = g.contentEquals(new StringBuffer().append("g").append(" hello")); // true
        boolean q = h.contentEquals(new StringBuilder().append("h").append(" hello"));// true
        boolean r = g.equalsIgnoreCase("g Hello");  // true

        System.out.println(Arrays.toString(new Object[]{o, p, q, r}));


        String s = "strs";
        String t = "Strt";
        int u = s.compareTo(t); // 32
        int v = s.compareToIgnoreCase(t);   // -1
        boolean w = s.regionMatches(true, 0, t, 0, 2);  // true
        boolean x = s.regionMatches(0, t, 0, 2);    // false
        boolean y = s.startsWith("S");      // false
        boolean z = t.startsWith("t", 1);   // true
        boolean z1 = s.endsWith("s");       // true

        System.out.println(Arrays.toString(new Object[]{u, v, w, x, y, z, z1}));


        String str = "abcdabcd";
        int i1 = str.indexOf(98);       // 1
        int i2 = str.indexOf(98, 2);    // 5
        int i3 = str.indexOf("d");      // 3
        int i4 = str.indexOf("d", 5);   // 7
        int i5 = str.lastIndexOf("a");  // 4
        int i6 = str.lastIndexOf("d", 3);   // 3

        String s1 = str.substring(3);   // dabcd
        String s2 = str.substring(1, 2);// b
        String s3 = str.concat("hehe"); // abcdabcdhehe
        String s4 = str.replace("hehe", "");// abcdabcd
        String s5 = str.replaceFirst("a", "b");// bbcdabcd
        String s6 = str.replaceAll("a", "b"); // bbcdbbcd

        System.out.println(Arrays.toString(
            new Object[]{i1, i2, i3, i4, i5, i6, s1, s2, s3, s4, s5, s6}));


        boolean b1 = str.matches("[a-d]+");     // true
        boolean b2 = str.contains("abc");       // true
        Arrays.asList(str.split("b")).forEach(System.out::println); // ["a","cda","cd"]
        Arrays.asList(str.split("b|d")).forEach(System.out::println); // ["a","c","a","c"]
        Arrays.asList(str.split("b|d", 3)).forEach(System.out::println); // ["a","c","abcd"]

        System.out.println(Arrays.toString(new Object[]{b1, b2}));

        
        String str1 = String.join(",", new String[]{" ","1","a","%s","B","%d"}); // " 1a%sB%d"
        String str2 = str1.toLowerCase();   //  " 1a%sb%d"
        String str3 = str1.toUpperCase();   //  " 1A%sB%d"
        String str4 = str1.trim();          //  "1a%sB%d"
        String str5 = String.format(str1, "haha", 1); //  " 1ahahaB1"

        Arrays.asList(new String[]{str1, str2, str3, str4, str5}).forEach(System.out::println);


        /* 
            常量池中没有 "StringTest" 字符串，str6.intern() 和 str6都是指向Java对象上的String对象
            常量池中一开始就已经存在 "java" 字符串，所以 str7.intern() 返回常量池中 "java" 字符串的引用。
         */
        String str6 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(str6.intern() == str6); // true

        String str7 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(str7.intern() == str7); // false

    }

}
