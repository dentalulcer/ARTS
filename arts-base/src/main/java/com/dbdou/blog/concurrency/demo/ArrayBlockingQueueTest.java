package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {

    public static void testAdd() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        queue.add(1);
        queue.offer(2);

        // 不允许null，会抛出 NullPointerException
        // queue.add(null);

        try {
            queue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 如果队列满了，则抛出异常 IllegalStateException
        // queue.add(4);
        // 如果队列满了，则返回false，不阻塞，不抛出异常
        queue.offer(5);

        try {
            // 可设置最大阻塞时间，如果队列仍是满的，则返回false，不阻塞，不抛出异常
            queue.offer(6, 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // 如果队列满了，则永远阻塞，不抛出异常
            queue.put(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testTake() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.add(1);
        queue.add(2);

        System.out.println(queue.peek());
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue);

        try {
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue);

    }

    public static void main(String[] args) {
        testAdd();
        testTake();
    }

}
