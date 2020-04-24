package com.dbdou.blog.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * -verbose:gc -Xmx4m -Xms4m -Xmn2m
 * Created by dentalulcer
 */
public class PhantomReferenceDemo {

    private static final List<Object> TEST_LIST = new LinkedList<>();
    private static final ReferenceQueue<TestClass> TEST_QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        TestClass tc = new TestClass("test phantomReference");
        PhantomReference<TestClass> pr = new PhantomReference<>(tc, TEST_QUEUE);

        // 该线程不断读取这个虚引用，并不断往列表里插入数据，以促使系统早点GC
        new Thread(() -> {
            while (true) {
                TEST_LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(pr.get());
            }
        }).start();

        // 该线程不断读取引用队列，当若引用指向当对象被回收时，该引用就会被加入到引用队列中
        new Thread(() -> {
            while (true) {
                Reference<? extends TestClass> poll = TEST_QUEUE.poll();
                if (poll != null) {
                    System.out.println("---虚引用对象被jvm回收了---" + poll);
                    System.out.println("---回收对象---" + poll.get());
                }

            }
        }).start();

        tc = null;
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class TestClass {
        private String name;

        TestClass(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
