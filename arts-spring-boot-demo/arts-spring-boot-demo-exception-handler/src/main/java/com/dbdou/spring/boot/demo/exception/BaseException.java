package com.dbdou.spring.boot.demo.exception;

import com.dbdou.spring.boot.demo.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by dentalulcer
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
