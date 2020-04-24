package com.dbdou.blog.proxy;

/**
 * Created by dentalulcer
 */
public class MyMain {

    public static void main(String[] args) {

        Buyer realBuyer = new RealBuyer();
        realBuyer.buyIphone();

        System.out.println("======================");

        Buyer proxyBuyer = new ProxyBuyer(realBuyer);
        proxyBuyer.buyIphone();

        System.out.println("======================");

        Buyer staticProxyBuyer = new StaticProxyBuyer(realBuyer);
        staticProxyBuyer.buyIphone();

        System.out.println("======================");

        JDKDynamicProxyHandler handler = new JDKDynamicProxyHandler(realBuyer);
        Buyer jdkDynamicProxyBuyer = (Buyer) handler.getProxy();
        jdkDynamicProxyBuyer.buyIphone();

        System.out.println("======================");

        CglibDynamicProxyInterceptor interceptor = new CglibDynamicProxyInterceptor();
        Buyer cglibDynamicProxyBuyer = (Buyer) interceptor.getInstance(realBuyer);
        cglibDynamicProxyBuyer.buyIphone();

    }

}
