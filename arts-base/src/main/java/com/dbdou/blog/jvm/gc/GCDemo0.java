package com.dbdou.blog.jvm.gc;

/**
 * 打印GC日志
 * -XX:+PrintGC     输出GC日志
 * -XX:+PrintGCDetails  输出GC详细日志
 * -XX:+PrintGCTimeStamps   输出GC时间戳
 * -XX:+PrintGCDateStamps   输出GC时间戳
 * -XX:+PrintHeapAtGC   在进行GC的前后打印出堆信息
 * -Xloggc:../logs/gc.log   日志文件的输出路径
 *
 * Created by dentalulcer
 */
public class GCDemo0 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            byte[] buffer = new byte[1024 * 1024];
        }

    }

}
