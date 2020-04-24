package com.dbdou.arts.url.core.filter;

import com.dbdou.arts.url.enums.FilterEnum;

/**
 * 布隆过滤器
 */
public abstract class AbstractBloomFilter implements Filter {

    /**
     * 加入 url 元素
     *
     * @param url
     * @return
     */
    boolean addUrl(String url) {
        return add(FilterEnum.URL_FILTER, url);
    }

    /**
     * url 过滤
     *
     * @param url
     * @return
     */
    boolean filterUrl(String url) {
        return exist(FilterEnum.URL_FILTER, url);
    }

    /**
     * 加入 code 元素
     *
     * @param code
     * @return
     */
    boolean addCode(String code) {
        return add(FilterEnum.CODE_FILTER, code);
    }

    /**
     * code 过滤
     *
     * @param code
     * @return
     */
    boolean filterCode(String code) {
        return exist(FilterEnum.CODE_FILTER, code);
    }

}
