package com.dbdou.arts.easy.url.core;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 轮询生成器
 *
 * Created by dentalulcer
 */
@Slf4j
public class PollingGenerator {

    /**
     * 访问次数
     */
    public static AtomicLong accessCount = new AtomicLong(1L);

}
