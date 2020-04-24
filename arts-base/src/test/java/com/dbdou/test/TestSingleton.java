package com.dbdou.test;

public class TestSingleton {

    private TestSingleton() {
    }

    public static TestSingleton getInstance() {
        return Inner.instance;
    }

    private static class Inner {
        private static TestSingleton instance = new TestSingleton();
    }

}
