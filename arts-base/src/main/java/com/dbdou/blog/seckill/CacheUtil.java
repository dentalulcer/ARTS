package com.dbdou.blog.seckill;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dentalulcer
 */
public class CacheUtil {

    /**
     * 模拟分布式锁
     */
    private static AtomicInteger lock = new AtomicInteger(0);

    /**
     * 库存
     */
    private static volatile int stock = 10000;

    public static int getStock() {
        return stock;
    }

    /**
     * 减库存
     * @return boolean
     */
    public static boolean decrStock() {
        if (stock <= 0) {
            return false;
        }
        stock -= 1;
        return true;
    }

    /**
     * 获取锁
     * @return boolean
     */
    public static boolean getLock() {
        return lock.compareAndSet(0, 1);
    }

    /**
     * 释放锁
     * @return boolean
     */
    public static boolean freeLock() {
        return lock.compareAndSet(1, 0);
    }

}
