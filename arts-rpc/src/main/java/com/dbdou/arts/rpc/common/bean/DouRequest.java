package com.dbdou.arts.rpc.common.bean;

import lombok.Data;

@Data
public class DouRequest {

    private String requestId;
    private String interfaceName;
    private String serviceVersion;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

}
