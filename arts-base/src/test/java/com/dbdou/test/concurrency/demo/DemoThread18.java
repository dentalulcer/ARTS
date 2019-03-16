package com.dbdou.test.concurrency.demo;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by dentalulcer on 09/03/2019.
 */
public class DemoThread18 {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        queue.add(1);
        queue.add(2);

        queue.offer(3);
        queue.offer(4);

//        queue.add(null);
        System.out.println(queue);

        System.out.println("peek=" + queue.peek());
        System.out.println("size=" + queue.size());

        System.out.println("poll=" + queue.poll());
        System.out.println("size=" + queue.size());

        System.out.println("poll=" + queue.poll());
        System.out.println("poll=" + queue.poll());
        System.out.println("poll=" + queue.poll());
        System.out.println("size=" + queue.size());

        System.out.println("peek=" + queue.peek());
        System.out.println("poll=" + queue.poll());

    }

}
