package com.dbdou.blog.ds;

/**
 * LRU 接口
 */
public interface LRUList<T> {

    /**
     * 添加元素
     *
     * @param item
     */
    void add(T item);

    /**
     * 根据下标获取
     *
     * @param index
     * @return
     */
    T get(int index);

}
