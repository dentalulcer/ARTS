package com.dbdou.test.concurrency.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by dentalulcer on 09/03/2019.
 */
public class DemoThread19 {

    public static void test1() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        queue.add(1);
        queue.offer(2);
//        queue.add(null);

        try {
            queue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        queue.add(4);
        System.out.println("a:" + queue);
        queue.offer(4);
        System.out.println("b:" + queue);

        try {
            queue.offer(5, 2, TimeUnit.SECONDS);
            System.out.println("c:" + queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            queue.put(6);
            System.out.println("d:" + queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void test2() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);
        queue.add(1);
        queue.add(2);

        System.out.println(queue.peek());
        System.out.println("a:" + queue);

        System.out.println(queue.poll());
        System.out.println("b:" + queue);

        try {
            System.out.println(queue.take());
            System.out.println("c:" + queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(queue.peek());
        System.out.println(queue.poll());



    }

    public static void main(String[] args) {

//        run1();

    }

}
