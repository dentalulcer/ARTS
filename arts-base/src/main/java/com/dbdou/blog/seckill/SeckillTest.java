package com.dbdou.blog.seckill;

/**
 * Created by dentalulcer
 */
public class SeckillTest {

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {

                drawDistriLock();

            }, "thread-" + i).start();
        }


    }

    /**
     * 分布式锁实现
     */
    private static void drawDistriLock() {
        System.out.print("请求开始：");
        // 检查库存
        if (CacheUtil.getStock() <= 0) {
            System.err.println("没库存了");
            return ;
        }
        System.out.println("有库存");

        long start = System.currentTimeMillis();
        // 获取锁
        while (!CacheUtil.getLock()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ignored) {
            }
            // 如果2s都没有获取到锁，返回0
            if (System.currentTimeMillis() - start > 2000) {
                System.out.println("获取锁失败");
                return ;
            }
        }

        // 减库存
        if (!CacheUtil.decrStock()) {
            CacheUtil.freeLock();
            return ;
        }
        // 发奖逻辑
        doBusiness();
        // 释放锁
        CacheUtil.freeLock();
    }

    /**
     * 业务处理
     */
    private static void doBusiness() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
