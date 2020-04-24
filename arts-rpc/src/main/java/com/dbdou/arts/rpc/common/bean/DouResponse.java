package com.dbdou.arts.rpc.common.bean;

import lombok.Data;

@Data
public class DouResponse {

    private String requestId;
    private Exception exception;
    private Object result;

}
