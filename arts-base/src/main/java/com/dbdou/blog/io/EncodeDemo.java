package com.dbdou.blog.io;

import java.io.UnsupportedEncodingException;

/**
 * Created by dentalulcer
 */
public class EncodeDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String s = "你好ABC";

        // utf-8 中文三个字节，英文1个字节
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            // 把字节（转换成int）以16进制形式显示
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // gbk 中文两个字节，英文1个字节
        byte[] gbks = s.getBytes("GBK");
        for (byte b : gbks) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // 用不同的编码方式，会乱码
        String str = new String(gbks);
        System.out.println(str);

        str = new String(gbks, "GBK");
        System.out.println(str);

    }

}
