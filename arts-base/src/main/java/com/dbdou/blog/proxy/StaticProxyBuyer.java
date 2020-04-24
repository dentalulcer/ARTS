package com.dbdou.blog.proxy;

/**
 * Created by dentalulcer
 */
public class StaticProxyBuyer implements Buyer {

    private Buyer buyer;

    public StaticProxyBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void buyIphone() {
        System.out.println("static proxy start!");
        this.buyer.buyIphone();
        System.out.println("static proxy end!");
    }

}
