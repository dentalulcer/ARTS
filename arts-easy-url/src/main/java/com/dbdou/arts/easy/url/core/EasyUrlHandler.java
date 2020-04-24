package com.dbdou.arts.easy.url.core;

import com.dbdou.arts.easy.url.entity.EasyUrl;
import com.dbdou.arts.easy.url.enums.KeyEnum;

/**
 * url 处理器
 *
 * Created by dentalulcer
 */
public class EasyUrlHandler {

    private static final String MY_DOMAIN = "http://dbdou.com/";

    /**
     * 包装短网址
     *
     * @param easyUrl
     * @return
     */
    public static String wrapUrl(EasyUrl easyUrl) {
        return MY_DOMAIN + KeyEnum.getByKey(easyUrl.getKey()).getLetter() + easyUrl.getCode();
    }

    /**
     * 根据短网址反向解析 EasyUrl 对象
     *
     * @param shortUrl
     * @return
     */
    public static EasyUrl parseUrl(String shortUrl) {
        shortUrl = shortUrl.replace(MY_DOMAIN, "");
        return new EasyUrl(0, KeyEnum.getByLetter(shortUrl.substring(0, 1)).getKey(), shortUrl.substring(1));
    }

}
