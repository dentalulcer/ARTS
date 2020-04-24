package com.dbdou.arts.url.service;

import com.dbdou.arts.url.core.filter.AbstractBloomFilter;
import com.dbdou.arts.url.core.generator.Generator;
import com.dbdou.arts.url.dao.UrlDao;
import com.dbdou.arts.url.entity.Url;
import com.dbdou.arts.url.enums.FilterEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 *
 */
@Service("hashService")
public class HashServiceImpl implements HashService {

    @Resource
    private UrlDao urlDao;

    @Resource
    private Generator hashGenerator;

    @Resource
    private AbstractBloomFilter redisBloomFilter;

    @Override
    public String create(String url) {
        String code = null;

        // 判断是否存在
        if (redisBloomFilter.exist(FilterEnum.URL_FILTER, url)) {
            // 查库，获取
            Url urlModel = urlDao.selectByUrl(DigestUtils.md5DigestAsHex(url.getBytes()));
            if (urlModel != null) {
                return urlModel.getCode();
            }
        }

        // 生成 code
        code = hashGenerator.generate(url);
        if (redisBloomFilter.exist(FilterEnum.CODE_FILTER, code)) {
            // 重新生成
            code = hashGenerator.generate(url + "_01");
        }

        // 保存 url bloomfilter
        redisBloomFilter.add(FilterEnum.URL_FILTER, url);
        // 保存 code bloomfilter
        redisBloomFilter.add(FilterEnum.CODE_FILTER, code);
        // 保存 url -> code 的映射
        Url urlModel = new Url();
        urlModel.setCode(code);
        urlModel.setUrl(url);
        urlModel.setUrlMd5(DigestUtils.md5DigestAsHex(url.getBytes()));
        urlDao.insert(urlModel);

        return code;
    }

    @Override
    public String forward(String code) {
        if (!redisBloomFilter.exist(FilterEnum.CODE_FILTER, code)) {
            // 不存在
            return null;
        }

        // 根据 code 查库得到 url
        Url urlModel = urlDao.selectByCode(code);
        if (urlModel == null) {
            return null;
        }

        return urlModel.getUrl();
    }

}
