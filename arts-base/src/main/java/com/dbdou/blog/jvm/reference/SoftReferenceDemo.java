package com.dbdou.blog.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * Created by dentalulcer
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {

        // 强引用
        Object obj = new Object();
        // 此时对象可以回收，但是具体什么时候回收不确定
        obj = null;

        Object obj1 = new Object();
        // 软引用
        SoftReference<Object> sr1 = new SoftReference<Object>(obj1);
        obj1 = null;
        // 此时进行垃圾回收，如果内存还是够用的，则不会进行回收
        System.gc();
        // 获取实例，依然能够获取到
        Object obj2 = sr1.get();
        System.out.println(obj2);

    }

}
