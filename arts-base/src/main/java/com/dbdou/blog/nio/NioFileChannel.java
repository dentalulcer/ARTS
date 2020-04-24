package com.dbdou.blog.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileChannel {

    private static void fileWrite() throws IOException {
        FileOutputStream fos = new FileOutputStream("/Users/a0000/test/java_test/nioFile.txt");
        FileChannel channel = fos.getChannel();

        String str = "hello world";

        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());

        buf.flip();
        channel.write(buf);
        fos.close();
    }

    public static void fileRead() throws IOException {
        File file = new File("/Users/a0000/test/java_test/nioFile.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();

        ByteBuffer buf = ByteBuffer.allocate((int) file.length());
        channel.read(buf);

        System.out.println(new String(buf.array()));
        fis.close();
    }

    public static void fileCopy() throws IOException {
        File file = new File("/Users/a0000/test/java_test/nioFile.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel inputChannel = fis.getChannel();

        File newFile = new File("/Users/a0000/test/java_test/nioFile2.txt");
        FileOutputStream fos = new FileOutputStream(newFile);
        FileChannel outputChannel = fos.getChannel();

        ByteBuffer buf = ByteBuffer.allocate((int) file.length());

        while (true) {
            buf.clear();
            int read = inputChannel.read(buf);
            if (read == -1) {
                break;
            }
            buf.flip();
            outputChannel.write(buf);
        }
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException {

//        fileWrite();
//
//        fileRead();

        fileCopy();

    }

}
