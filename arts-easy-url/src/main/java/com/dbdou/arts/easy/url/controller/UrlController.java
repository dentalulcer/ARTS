package com.dbdou.arts.easy.url.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.dbdou.arts.easy.url.core.AsyncProcessor;
import com.dbdou.arts.easy.url.core.CodeGenerator;
import com.dbdou.arts.easy.url.core.EasyUrlHandler;
import com.dbdou.arts.easy.url.core.PollingGenerator;
import com.dbdou.arts.easy.url.dao.UrlDao;
import com.dbdou.arts.easy.url.entity.BaseResp;
import com.dbdou.arts.easy.url.entity.EasyUrl;
import com.dbdou.arts.easy.url.enums.KeyEnum;
import com.dbdou.arts.easy.url.enums.RespCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by dentalulcer
 */
@Slf4j
@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlDao urlDao;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private AsyncProcessor asyncProcessor;

    /**
     * 生成简码 code
     * <p>
     * TODO
     * 1. 一次性取回 incrFactor 个数，做个随机排序处理，避免连续生成的 url 相似度过高
     * 2. 跳过连续字母的结果
     *
     * @param url
     * @return
     */
    @PostMapping("/create")
    public BaseResp create(@RequestBody String url) {
        long starTm = System.currentTimeMillis();
        // 0. 校验 url
        try {
            url = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!Validator.isMactchRegex(Validator.URL_HTTP, url)) {
            return BaseResp.builder().code(RespCodeEnum.PARAM_ILLEGAL.getCode()).build();
        }

        // 1. 指定 key（随机、轮询、hash）
        String key = chooseKeyHash(url);

        // 2. 判断 url 是否存在
        EasyUrl easyUrl = urlDao.selectByUrl(new EasyUrl(key, url));
        if (easyUrl != null) {
            return BaseResp.builder().code(RespCodeEnum.SUCCESS.getCode()).data(EasyUrlHandler.wrapUrl(easyUrl)).build();
        }

        // 3. 生成简码 code
        easyUrl = codeGenerator.generator(key);

        // 4. 异步保存入库
        easyUrl.setUrl(url);
        log.info("url generator: easyUrl={}", JSONUtil.toJsonStr(easyUrl));

        try {
            asyncProcessor.execute(easyUrl);
        } catch (InterruptedException e) {
            log.error("gen url error: easyUrl={}", JSONUtil.toJsonStr(easyUrl), e);
            return BaseResp.builder().code(RespCodeEnum.SERVICE_BUSY.getCode()).build();
        }
        long endTm = System.currentTimeMillis();
        log.info("create costs: {} ms", (endTm - starTm));
        // 5. 响应给前端
        return BaseResp.builder().code(RespCodeEnum.SUCCESS.getCode()).data(EasyUrlHandler.wrapUrl(easyUrl)).build();
    }

    /**
     * 根据简码 code 反解析 url
     *
     * @param shortUrl
     * @return
     */
    @PostMapping("/query")
    public BaseResp query(@RequestBody String shortUrl) {
        EasyUrl easyUrl = urlDao.selectByCode(EasyUrlHandler.parseUrl(shortUrl));
        if (easyUrl != null) {
            return BaseResp.builder().code(RespCodeEnum.SUCCESS.getCode()).data(easyUrl.getUrl()).build();
        }
        return BaseResp.builder().code(RespCodeEnum.NOT_FOUND.getCode()).build();
    }

    private String chooseKeyRandom() {
        return RandomUtil.randomEle(KeyEnum.values()).getKey();
    }

    private String chooseKeyPolling() {
        long accessCount = PollingGenerator.accessCount.getAndIncrement();
        return KeyEnum.values()[(int) (accessCount % KeyEnum.values().length)].getKey();
    }

    private String chooseKeyHash(String url) {
        return KeyEnum.values()[HashUtil.additiveHash(url, KeyEnum.values().length)].getKey();
    }

}
