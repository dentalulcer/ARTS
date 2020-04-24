package com.dbdou.blog.proxy;

/**
 * Created by dentalulcer
 */
public class RealBuyer implements Buyer {

    @Override
    public void buyIphone() {
        System.out.println("real buy iphone!");
    }

}
