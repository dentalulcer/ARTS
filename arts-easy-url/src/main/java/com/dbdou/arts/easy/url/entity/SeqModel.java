package com.dbdou.arts.easy.url.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dentalulcer
 */
@Data
public class  SeqModel implements Serializable {

    /**
     * key
     */
    private String key;

    /**
     * 增长因子
     */
    private long incrFactor;

    /**
     * 当前值
     */
    private AtomicLong currentVal = new AtomicLong(1L);

    /**
     * 当前阈值
     */
    private long thresholdVal = 0L;

    public SeqModel() {}

    public SeqModel(String key) {
        this.key = key;
    }

}
