package com.dbdou.arts.easy.url.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by dentalulcer
 */
@Data
public class KeySeq implements Serializable {

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
    private long currentVal;

}
