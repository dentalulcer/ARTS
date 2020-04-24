package com.dbdou.arts.easy.url.core;

import cn.hutool.core.util.StrUtil;
import com.dbdou.arts.easy.url.entity.EasyUrl;
import com.dbdou.arts.easy.url.enums.KeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 简码 code 生成器
 *
 * Created by dentalulcer
 */
@Slf4j
@Component
public class CodeGenerator {

    @Autowired
    private SeqGenerator seqGenerator;

    /**
     * 生成简码 code
     * @param key
     * @return
     */
    public EasyUrl generator(String key) {
        long id = seqGenerator.generate(key);
        long seqId = id;

        KeyEnum keyEnum = KeyEnum.getByKey(key);
        String seeds = keyEnum.getSeeds();
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(seeds.charAt((int) (seqId % seeds.length())));
            seqId = seqId / seeds.length();
        } while (seqId > 0);

        String code = StrUtil.fillBefore(StrUtil.reverse(sb.toString()), seeds.charAt(0), 6);
        return new EasyUrl(id, key, code);
    }

}
