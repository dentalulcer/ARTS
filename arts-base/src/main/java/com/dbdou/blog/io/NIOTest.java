package com.dbdou.blog.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by dentalulcer
 */
public class NIOTest {

    private static final String TEST_BASE_DIR = File.separator + "Users" + File.separator + "a0000"
            + File.separator + "test" + File.separator + "java_test";

    public static void main(String[] args) throws IOException {
        String file = TEST_BASE_DIR + File.separator + "hello.txt";

        ioRead(file);

        nioRead(file);

    }

    private static void ioRead(String file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[16];
        fis.read(bytes);
        System.out.println(new String(bytes));
    }

    private static void nioRead(String file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        channel.read(byteBuffer);
        byte[] bytes = byteBuffer.array();
        System.out.println(new String(bytes));
    }

}
