package com.dbdou.test.io;

import java.io.*;

/**
 *
 * Created by dentalulcer on 2019/3/29.
 */
public class FileRWTest {

    private static final String TEST_BASE_DIR = File.separator + "Users" + File.separator + "a0000"
            + File.separator + "test" + File.separator + "java_test";

    public static void main(String[] args) {
        File file = new File(TEST_BASE_DIR, "hello2.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        test1();
//        test2();
        test3();

    }

    private static void test3() {

    }

    private static void test2() {
        File file = new File(TEST_BASE_DIR, "hello2.txt");
        try (FileReader fr = new FileReader(file)) {
            char[] chars = new char[100];
            int read = fr.read(chars);
            System.out.println(new String(chars, 0, read));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        File file = new File(TEST_BASE_DIR, "hello2.txt");
        try(FileWriter fw = new FileWriter(file)) {
            fw.write("你好");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
