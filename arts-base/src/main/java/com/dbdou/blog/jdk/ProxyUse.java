package com.dbdou.blog.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dentalulcer
 */
public class ProxyUse {

    public interface IMyProxy {
        public void say();
    }

    static class MyProxyImpl implements IMyProxy {
        @Override
        public void say() {
            System.out.println("say hello!");
        }
    }

    static class MyInvocationHandler implements InvocationHandler {
        // 目标对象
        private Object target;
        public MyInvocationHandler(Object target) {
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before");
            Object result = method.invoke(target, args);
            System.out.println("after");
            return result;
        }
        public Object getProxy() {
            return Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    this.target.getClass().getInterfaces(),
                    this);
        }
    }

    public static void main(String[] args) {

        MyInvocationHandler mih = new MyInvocationHandler(new MyProxyImpl());
        IMyProxy myProxy = (IMyProxy) mih.getProxy();
        myProxy.say();

    }

}
