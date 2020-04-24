package com.dbdou.arts.easy.url.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by dentalulcer
 */
@Data
public class EasyUrl implements Serializable {

    private long id;

    private String key;

    private String url;

    private String code;

    public EasyUrl() {
    }

    public EasyUrl(String key, String url) {
        this.key = key;
        this.url = url;
    }

    public EasyUrl(long id, String key, String code) {
        this.id = id;
        this.key = key;
        this.code = code;
    }

}
