package com.dbdou.arts.sword;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * <p>例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。</p>
 */
public class No002ReplaceBlank {

    public static void main(String[] args) {

        System.out.println(replaceSpace(new StringBuffer("We Are Happy")));

    }

    public static String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        return str.toString().replace(" ", "%20");
    }

}
