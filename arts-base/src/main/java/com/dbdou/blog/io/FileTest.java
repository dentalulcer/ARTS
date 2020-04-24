package com.dbdou.blog.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by dentalulcer
 */
public class FileTest {

    private static final String TEST_BASE_DIR = File.separator + "Users" + File.separator + "a0000"
            + File.separator + "test" + File.separator + "java_test";

    public static void main(String[] args) {

//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();


    }

    private static void test7() {
        try {
            RandomAccessFile raf = new RandomAccessFile(TEST_BASE_DIR + File.separator + "hello.txt", "r");
//            raf.seek(raf.length());
//            raf.write("abc".getBytes());

//            System.out.println(raf.length());
//            raf.seek(0);
//            byte[] buff = new byte[1024];
//            int hasRead = 0;
//            while ((hasRead = raf.read()) > 0) {
//                System.out.println(new String(buff, 0, hasRead));
//            }

            System.out.println(new String(raf.readLine().getBytes("ISO-8859-1"), "utf-8"));

            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test6() {
        List<String> list = new ArrayList<>();
        File file = new File(TEST_BASE_DIR);
        test61(file, list);
        for (String aList : list) {
            System.out.println(aList);
        }
    }

    private static void test61(File file, List<String> list) {
        list.add(file.getAbsolutePath());
        if (file.isDirectory() && file.listFiles() != null && file.listFiles().length > 0) {
            for (int i = 0; i < file.listFiles().length; i++) {
                test61(file.listFiles()[i], list);
            }
        }
    }

    private static void test5() {
        File dir = new File(TEST_BASE_DIR);
        String[] list = dir.list();
        File[] files = dir.listFiles();
        if (list == null || files == null) {
            System.out.println("文件夹不存在");
            return ;
        }
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
            System.out.println(files[i].getAbsolutePath());
        }
    }

    private static void test4() {
        File dir = new File(TEST_BASE_DIR + File.separator + "dir2");
        if (dir.isDirectory()) {
            System.out.println("is dir");
        } else {
            System.out.println("is not dir");
        }
        dir.mkdir();
        if (dir.isDirectory()) {
            System.out.println("is dir");
        } else {
            System.out.println("is not dir");
        }
    }

    private static void test3() {
        File file = new File(TEST_BASE_DIR + File.separator + "hello2.txt");
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("file not exists");
        }
    }

    private static void test2() {
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
    }

    private static void test1() {
        File file = new File(TEST_BASE_DIR + File.separator + "hello2.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
