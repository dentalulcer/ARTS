package com.dbdou.arts.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dentalulcer
 */
@Component
public class Ex004Bean {

    @Autowired
    private InnerBean01 innerBean01;

    private InnerBean02 innerBean02;

    private InnerBean03 innerBean03;


    @Autowired
    private Ex004Bean(InnerBean03 innerBean03) {
        this.innerBean03 = innerBean03;
    }

    @Autowired
    public void setInnerBean02(InnerBean02 innerBean02) {
        this.innerBean02 = innerBean02;
    }

    @Override
    public String toString() {
        return "ex004Bean info : innerBean01=" + innerBean01
                + ", innerBean02=" + innerBean02 + ", innerBean03=" + innerBean03;
    }
}

@Component
class InnerBean01 {

}

@Component
class InnerBean02 {

}

@Component
class InnerBean03 {

}

