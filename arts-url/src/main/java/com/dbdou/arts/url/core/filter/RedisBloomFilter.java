package com.dbdou.arts.url.core.filter;

import com.dbdou.arts.url.enums.FilterEnum;
import com.dbdou.arts.url.util.BloomFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 布隆过滤器的 redis 实现
 */
@Slf4j
@Component("redisBloomFilter")
public class RedisBloomFilter extends AbstractBloomFilter {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean add(FilterEnum type, String data) {
        int[] bitArr = BloomFilterUtil.createHashes(data, type.getHashNum());
        log.debug("bloom filter set key={}, hashNum={}", type.getKey(), type.getHashNum());
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (int i : bitArr) {
                log.debug("bloom filter key={}, data={}, bit={}", type.getKey(), data, i);
                connection.setBit(type.getKey().getBytes(), i, true);
            }
            return null;
        });
        return true;
    }

    @Override
    public boolean exist(FilterEnum type, String data) {
        int[] bitArr = BloomFilterUtil.createHashes(data, type.getHashNum());
        List<Object> result = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (int i : bitArr) {
                connection.getBit(type.getKey().getBytes(), i);
            }
            return null;
        });
        return result.stream().allMatch((item) -> (boolean) item);
    }

}
