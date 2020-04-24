package com.dbdou.blog.concurrency.demo;

/**
 * Created by dentalulcer.
 */
public class DemoThread06 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                Child child = new Child();
                child.runChild();
            }
        });

        t1.start();
    }

}

class Parent {
    public int i = 10;
    public synchronized void runParent() {
        try {
            i--;
            System.out.println("Parent>>>i=" + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Child extends Parent {
    public synchronized void runChild() {
        while (i > 0) {
            try {
                i--;
                System.out.println("Child>>>i=" + i);
                Thread.sleep(100);
                //调用父类synchronized方法，不会引起死锁
                runParent();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}