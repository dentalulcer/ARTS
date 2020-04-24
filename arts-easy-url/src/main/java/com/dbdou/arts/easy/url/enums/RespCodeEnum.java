package com.dbdou.arts.easy.url.enums;

import lombok.Getter;

/**
 * 响应 code 枚举
 *
 * Created by dentalulcer
 */
public enum RespCodeEnum {

    SUCCESS(200, "处理成功"),
    SERVICE_BUSY(500, "服务端忙,请稍后重试"),

    // 基础校验相关
    PARAM_ILLEGAL(400, "参数校验失败"),
    NOT_FOUND(404,"请求资源不存在");

    @Getter
    private int code;

    @Getter
    private String desc;

    RespCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
