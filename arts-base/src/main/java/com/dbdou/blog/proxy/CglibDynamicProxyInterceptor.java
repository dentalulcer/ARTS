package com.dbdou.blog.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by dentalulcer
 */
public class CglibDynamicProxyInterceptor implements MethodInterceptor {

    private Object targetObject;

    public Object getInstance(Object targetObject) {
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.targetObject.getClass());
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib dynamic proxy start!");
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("cglib dynamic proxy end!");
        return result;
    }

}
