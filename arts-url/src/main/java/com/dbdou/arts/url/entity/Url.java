package com.dbdou.arts.url.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by dentalulcer
 */
@Data
public class Url implements Serializable {

    private long id;

    private String url;

    private String code;

    private String urlMd5;

    public Url() {
    }

    public Url(String url) {
        this.url = url;
    }

    public Url(long id, String code) {
        this.id = id;
        this.code = code;
    }

}
