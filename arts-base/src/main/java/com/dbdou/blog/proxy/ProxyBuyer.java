package com.dbdou.blog.proxy;

/**
 * Created by dentalulcer
 */
public class ProxyBuyer implements Buyer {

    private Buyer buyer;

    public ProxyBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void buyIphone() {
        System.out.println("proxy buy start!");
        this.buyer.buyIphone();
        System.out.println("proxy buy end!");
    }

}
