package com.dbdou.arts.easy.url.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 通用响应对象
 *
 * Created by dentalulcer
 */
@Data
@Builder
public class BaseResp {

    private int code;

    private String desc;

    private Object data;

}
