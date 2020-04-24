package com.dbdou.test;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        System.out.println(red(10, 100));

    }

    // 1.题目：请编写一个红包随机算法。
    // 给定一定的金额，一定的人数，保证每个人都能随机获得一定的金额。
    // 比如100元的红包，10个人抢，每人分得一些金额。约束条件为，最佳手气金额不能超过最大金额的90%。
    // 请给出java代码
    private static double[] red(int count, int amount) {
        double[] result = new double[]{count};
        double real = amount * 100;
        double total = 0;
        // 转化
        for (int i = 0; i < count - 1; i++) {
            double random = Math.random();
            double tmp = Math.round((real - total) * random * 0.9);
            System.out.println((tmp / 100));
            result[i] = tmp;
            total += tmp;
            real -= tmp;
        }
        result[count] = (real - total);
        return result;
    }


    // 2.线程A负责实时往Map里put 1-100的数字，线程B负责实时从这个map中get数字并进行累加
    //（A放入MAP一个值后，B取出来,然后A继续放，B继续取，以此循环一直到A放完100，B取完100,结束），
    // B实时打印当前时刻累加的结果。
    private static void test2() {
        Map<Integer, Object> map = new HashMap<>(100);
    }


}
