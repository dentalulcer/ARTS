package com.dbdou.arts.url.core.generator;

import com.dbdou.arts.url.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * hash 算法生成器
 */
@Slf4j
@Component("hashGenerator")
public class HashGenerator implements Generator {

    @Override
    public String generate(String url) {
        int hash = Math.abs(HashUtil.murmur32(url.getBytes()));
        String code = HashUtil.num2Hex(hash);
        log.debug("hash generator url={}, hash={}, code={}", url, hash, code);
        return code;
    }

}
