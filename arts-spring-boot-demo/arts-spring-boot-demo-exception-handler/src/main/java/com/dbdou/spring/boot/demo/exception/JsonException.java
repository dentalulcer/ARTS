package com.dbdou.spring.boot.demo.exception;

import com.dbdou.spring.boot.demo.constant.Status;

/**
 * Created by dentalulcer
 */
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }

}
