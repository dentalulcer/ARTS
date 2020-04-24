package com.dbdou.arts.url.core.filter;

import com.dbdou.arts.url.enums.FilterEnum;

/**
 * 过滤器
 */
public interface Filter {

    /**
     * 加入元素
     *
     * @param type
     * @param data
     * @return
     */
    boolean add(FilterEnum type, String data);

    /**
     * 判断元素是否存在
     *
     * @param type
     * @param data
     * @return
     */
    boolean exist(FilterEnum type, String data);

}
