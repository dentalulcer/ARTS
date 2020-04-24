package com.dbdou.blog.seckill;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by dentalulcer
 */
@Data
public class Gift implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private int owner;

}
