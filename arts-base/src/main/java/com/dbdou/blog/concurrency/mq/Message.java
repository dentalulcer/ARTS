package com.dbdou.blog.concurrency.mq;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter
public class Message implements Serializable {

    private String name;

}
