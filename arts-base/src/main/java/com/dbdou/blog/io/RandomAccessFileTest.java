package com.dbdou.blog.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by dentalulcer .
 */
public class RandomAccessFileTest {

    private static final String TEST_BASE_DIR = File.separator + "Users" + File.separator + "a0000"
            + File.separator + "test" + File.separator + "java_test";

    public static void main(String[] args) throws IOException {
        File file = new File(TEST_BASE_DIR, "raf.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        System.out.println(raf.getFilePointer());

        raf.write('A'); // 写一个字节
        System.out.println(raf.getFilePointer());

        int i = 0x7fffffff;
        // 用write方法每次只能写一个字节，如果把i写进去需要写4次
        raf.write(i >>> 24);
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);
        System.out.println(raf.getFilePointer());

        raf.writeInt(i);
        System.out.println(raf.getFilePointer());

        String s = "中";
        byte[] bytes = s.getBytes("UTF-8");
        raf.write(bytes);
        System.out.println(raf.length());

        // 读文件，必须把指针移到头部
        raf.seek(0);
    }

}
