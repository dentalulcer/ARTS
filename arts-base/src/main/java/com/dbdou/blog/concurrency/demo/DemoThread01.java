package com.dbdou.blog.concurrency.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by dentalulcer
 */
public class DemoThread01 {

    private static class Account {
        private String name;
    }

    private static class CreateThreadImplThread extends Thread {
        private Account account;
        CreateThreadImplThread(Account account) {
            this.account = account;
        }
        @Override
        public void run() {
            System.out.println("==== " + this.account.name + " ====");
        }
    }

    private static class CreateThreadImplRunnable implements Runnable {
        private Account account;
        CreateThreadImplRunnable(Account account) {
            this.account = account;
        }
        @Override
        public void run() {
            System.out.println("==== " + this.account.name + " ====");
        }
    }

    private static class CreateThreadImplCallable implements Callable<Account> {
        private Account account;
        CreateThreadImplCallable(Account account) {
            this.account = account;
        }
        @Override
        public Account call() throws Exception {
            System.out.println("==== " + this.account.name + " ====");
            this.account.name = "mary";
            Thread.sleep(3000);
            return this.account;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        account.name = "tom";

        CreateThreadImplThread t1 = new CreateThreadImplThread(account);
        t1.start();
        Thread.sleep(1000);
        account.name = "jack";
        CreateThreadImplThread t2 = new CreateThreadImplThread(account);
        t2.start();

        CreateThreadImplRunnable t3 = new CreateThreadImplRunnable(account);
        new Thread(t3).start();
        Thread.sleep(1000);
        account.name = "mike";
        CreateThreadImplRunnable t4 = new CreateThreadImplRunnable(account);
        new Thread(t4).start();

        CreateThreadImplCallable t5 = new CreateThreadImplCallable(account);
        FutureTask<Account> ft = new FutureTask<>(t5);
        new Thread(ft).start();
        try {
            System.out.println("---- start get ----");
            Account result = ft.get();
            System.out.println("---- " + result.name + " ----");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
