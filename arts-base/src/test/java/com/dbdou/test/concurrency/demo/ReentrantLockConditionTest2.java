package com.dbdou.test.concurrency.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dentalulcer on 10/03/2019.
 */
public class ReentrantLockConditionTest2 {

    ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public void run1() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法run1等待");
            c1.wait();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "方法run1继续");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void run2() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法run2等待");
            c1.wait();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "方法run2继续");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void run3() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入方法run3等待");
            c2.wait();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "方法run3继续");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void run4() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "run4唤醒");
            c1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void run5() {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "run5唤醒");
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final ReentrantLockConditionTest2 test = new ReentrantLockConditionTest2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.run1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.run2();
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.run3();
            }
        }, "t3");

        Thread t4= new Thread(new Runnable() {
            @Override
            public void run() {
                test.run4();
            }
        }, "t4");

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.run5();
            }
        }, "t5");

//        t1.start();
        t2.start();
//        t3.start();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        t4.start();
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        t5.start();

    }

}
