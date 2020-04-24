package com.dbdou.arts.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by dentalulcer
 */
@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(com.dbdou.arts.spring.aop.innotation.MyAnnotation)")
    public void cut() {

    }

    @Around("cut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("method start: " + System.currentTimeMillis());
        pjp.proceed();
        System.out.println("method end: " + System.currentTimeMillis());
    }

}
