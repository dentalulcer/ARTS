package com.dbdou.blog.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dentalulcer
 */
public class JDKDynamicProxyHandler implements InvocationHandler {

    // 目标对象
    private Object targetObject;

    public JDKDynamicProxyHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                this.targetObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk dynamic proxy start!");
        Object result = method.invoke(targetObject, args);
        System.out.println("jdk dynamic proxy end!");
        return result;
    }

}
