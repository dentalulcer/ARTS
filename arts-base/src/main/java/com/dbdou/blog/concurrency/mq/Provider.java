package com.dbdou.blog.concurrency.mq;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Provider implements Runnable {

    private String name;
    private LinkedBlockingQueue<Message> queue;

    private volatile boolean running = true;

    public Provider(String name, LinkedBlockingQueue<Message> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = new Message();
            message.setName("msg" + System.currentTimeMillis());
            try {
                if (queue.offer(message, 2, TimeUnit.SECONDS)) {
                    System.out.println("生产者：" + name + "生产了" + message.getName());
                } else {
                    System.out.println("生产者：" + name + "生产超时");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        System.out.println("生产者：" + name + "停止生产");
        this.running = false;
    }

}
