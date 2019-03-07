package com.dbdou.test.concurrency.demo;

/**
 * Created by dentalulcer on 07/03/2019.
 */
public class ThreadPlayground {

    public static void main(String[] args) {
//        SingletonBean bean = SingletonBean.getBean();
        ThreadBean bean = new ThreadBean();

        ThreadDemo demo1 = new ThreadDemo("mary", 10, bean);
        ThreadDemo demo2 = new ThreadDemo("mike", 20, bean);

        new Thread(demo1).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(demo2).start();

    }

}
