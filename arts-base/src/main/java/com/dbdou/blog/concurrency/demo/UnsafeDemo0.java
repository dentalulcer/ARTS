package com.dbdou.blog.concurrency.demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo0 {

    private int age;

    public int getAge() {
        return age;
    }

    public static void test1() throws NoSuchFieldException {
        UnsafeDemo0 demo = new UnsafeDemo0();

        Unsafe unsafe = Unsafe.getUnsafe();
        long ageOffset = unsafe.objectFieldOffset(demo.getClass().getDeclaredField("age"));

        System.out.println(ageOffset);
    }

    public static void test2() throws NoSuchFieldException, IllegalAccessException {
        UnsafeDemo0 demo = new UnsafeDemo0();

        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);

        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        long ageOffset = unsafe.objectFieldOffset(UnsafeDemo0.class.getDeclaredField("age"));
        System.out.println(ageOffset);

        int age = unsafe.getInt(demo, ageOffset);
        System.out.println(age);
        unsafe.putInt(demo, ageOffset, 10);
        age = unsafe.getInt(demo, ageOffset);
        System.out.println(age);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

//        test1();
        test2();
    }

}
