package com.dbdou.blog.jvm.loader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dentalulcer.
 */
public class MyClassLoader extends ClassLoader {

    private String myPath;

    public MyClassLoader(String path) {
        myPath = path;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.replace('.', '/').concat(".class");
        File file = new File(myPath, fileName);
        System.out.println("加载：" + file.getAbsolutePath());
        try {
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = bos.toByteArray();
            is.close();
            bos.close();
            // 将class字节码的二进制流，转化成Class对象
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 调用父类的findClass
        return super.findClass(name);
    }

    public static void main(String[] args) {

        MyClassLoader myClassLoader = new MyClassLoader("/Users/a0000/test/java_test");

        // 查看父加载器
        ClassLoader parentClassLoader = myClassLoader.getParent();
        System.out.println("parentClassLoader:" + parentClassLoader);

        try {
            Class clazz = myClassLoader.loadClass("Hello");
            if (clazz != null) {
                Object instance = clazz.newInstance();
                Method method = clazz.getDeclaredMethod("say");
                method.invoke(instance);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
