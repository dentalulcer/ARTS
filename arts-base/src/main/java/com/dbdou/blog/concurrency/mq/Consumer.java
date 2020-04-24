package com.dbdou.blog.concurrency.mq;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private String name;
    private LinkedBlockingQueue<Message> queue;

    public Consumer(String name, LinkedBlockingQueue<Message> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(new Random().nextInt(1000));
                Message message = queue.poll(1, TimeUnit.SECONDS);
                if (message != null) {
                    System.out.println("消费者：" + name + "消费了" + message.getName());
                } else {
                    System.out.println("消费者：" + name + "消费超时");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
