package com.dbdou.spring.boot.demo.exception;

import com.dbdou.spring.boot.demo.constant.Status;

/**
 * Created by dentalulcer
 */
public class PageException extends BaseException {

    public PageException(Status status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }

}
