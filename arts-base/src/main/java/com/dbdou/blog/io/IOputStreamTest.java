package com.dbdou.blog.io;

import java.io.*;

/**
 * Created by dentalulcer
 */
public class IOputStreamTest {

    private static final String TEST_BASE_DIR = File.separator + "Users" + File.separator + "a0000"
            + File.separator + "test" + File.separator + "java_test";

    public static void main(String[] args) {
        test();
        test1();
        test2();
        test3();
    }

    private static void test3() {
        File file = new File(TEST_BASE_DIR, "hello.txt");
        byte[] bytes;
        try (InputStream is = new FileInputStream(file)) {
            bytes = new byte[1024];
            int hasRead = 0;
            int offset = 0;
            while ((hasRead = is.read()) != -1) {
                bytes[offset++] = (byte) hasRead;
            }
            System.out.println(new String(bytes, 0, offset));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test2() {
        File file = new File(TEST_BASE_DIR, "hello.txt");
        byte[] bytes;
        try (InputStream is = new FileInputStream(file)) {
            bytes = new byte[(int) file.length()];
            is.read(bytes);
            System.out.println(new String(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        File file = new File(TEST_BASE_DIR, "hello.txt");
        String str = " 世界";
        byte[] bytes = str.getBytes();
        try (OutputStream outputStream = new FileOutputStream(file, true)) {
            for (int i = 0; i < bytes.length; i++) {
                outputStream.write(bytes[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        File file = new File(TEST_BASE_DIR, "hello.txt");
        String str = "你好";
        byte[] bytes = str.getBytes();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < bytes.length; i++) {
                outputStream.write(bytes[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
