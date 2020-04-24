package com.dbdou.arts.url.util;

/**
 * bloom filter 辅助类
 */
public class BloomFilterUtil {

    private BloomFilterUtil() {
    }

    /**
     * 计算需要多少个bit位
     *
     * @param n 期望元素数量
     * @param p 误判率
     * @return
     */
    public static long optimalM(long n, double p) {
        return (long) Math.ceil(-1 * (n * Math.log(p)) / Math.pow(Math.log(2), 2));
    }

    /**
     * 计算需要多少个hash函数
     *
     * @param n 期望元素数量
     * @param m bit位数量
     * @return
     */
    public static int optimalK(long n, long m) {
        return (int) Math.ceil((Math.log(2) * m) / n);
    }

    /**
     * 将字符串的字节表示进行多哈希编码.
     *
     * @param str        待添加进过滤器的字符串字节表示.
     * @param hashNumber 要经过的哈希个数.
     * @return 各个哈希的结果数组.
     */
    public static int[] createHashes(String str, int hashNumber) {
        int[] result = new int[hashNumber];
        for (int i = 0; i < hashNumber; i++) {
            result[i] = Math.abs(hash(str, i));
        }
        return result;
    }

    /**
     * 计算Hash值
     *
     * @param str 被计算Hash的字符串
     * @param k   Hash算法序号
     * @return Hash值
     */
    public static int hash(String str, int k) {
        switch (k) {
            case 0:
                return HashUtil.rsHash(str);
            case 1:
                return HashUtil.jsHash(str);
            case 2:
                return HashUtil.elfHash(str);
            case 3:
                return HashUtil.bkdrHash(str);
            case 4:
                return HashUtil.apHash(str);
            case 5:
                return HashUtil.djbHash(str);
            case 6:
                return HashUtil.sdbmHash(str);
            case 7:
                return HashUtil.pjwHash(str);
            default:
                return 0;
        }
    }

}
