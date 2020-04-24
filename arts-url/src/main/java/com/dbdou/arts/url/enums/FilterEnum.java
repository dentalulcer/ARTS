package com.dbdou.arts.url.enums;

import com.dbdou.arts.url.util.BloomFilterUtil;
import lombok.Getter;

/**
 * 过滤器枚举
 */
public enum FilterEnum {

    /**
     * 地址过滤
     */
    URL_FILTER("URL_FILTER", "arts:url:filter:url", 100_000L, 0.01),
    /**
     * coode 过滤
     */
    CODE_FILTER("CODE_FILTER", "arts:url:filter:code", 100_000L, 0.01);

    /**
     * code
     */
    @Getter
    private String code;

    @Getter
    private String key;

    /**
     * 期望元素数量
     */
    private long size;

    /**
     * 误判率
     */
    private double fpp;

    /**
     * bit 数组长度
     */
    private long bitNum;

    /**
     * hash 函数数量
     */
    private int hashNum;

    public long getBitNum() {
        if (bitNum == 0) {
            bitNum = BloomFilterUtil.optimalM(this.size, fpp);
        }
        return bitNum;
    }

    public int getHashNum() {
        if (hashNum == 0) {
            hashNum = BloomFilterUtil.optimalK(this.size, getBitNum());
        }
        return hashNum;
    }

    FilterEnum(String code, String key, long size, double fpp) {
        this.code = code;
        this.key = key;
        this.size = size;
        this.fpp = fpp;
    }

}
