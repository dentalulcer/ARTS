package com.dbdou.blog.jvm.gc;

/**
 * Created by dentalulcer
 */
public class FinalizeDemo {

    public static FinalizeDemo demo = null;

    public void isAlive() {
        System.out.println("对象还活着……");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize 被执行");
        FinalizeDemo. demo = this;
    }

    public static void main(String[] args) {

        demo = new FinalizeDemo();

        demo = null;
        System.gc();

        // 因为finalize()方法优先级较低，所以暂停0.5s等待
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (demo != null) {
            demo.isAlive();
        } else {
            System.out.println("1对象被回收");
        }

        demo = null;
        System.gc();
        // 因为finalize()方法优先级较低，所以暂停0.5s等待
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (demo != null) {
            demo.isAlive();
        } else {
            System.out.println("2对象被回收");
        }

    }

}
