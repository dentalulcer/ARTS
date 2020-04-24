package com.dbdou.blog.jdk;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dentalulcer
 */
public class ArrayListTest {

    public static void main(String[] args) {

//        testOverFlow();

//        testEnsureCapacity();

        testIteratorRemove();

    }

    private static void testIteratorRemove() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");

        // right operate
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str);
            if ("b".equals(str)) {
                iterator.remove();
            }
        }
        System.out.println(list);

        // wrong operate: 遍历错位（少遍历一项）
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            if ("c".equals(list.get(i))) {
                list.remove(i);
            }
        }
        System.out.println(list);

        // wrong operate: 遍历错位（少遍历一项）
        for (int i = list.size()-1; i >= 0 ; i--) {
            System.out.println(list.get(i));
            if ("d".equals(list.get(i))) {
                list.remove(i);
            }
        }
        System.out.println(list);

        // wrong operate: ConcurrentModificationException
        for (String str : list) {
            System.out.println(str);
            if ("e".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list);
    }

    private static void testEnsureCapacity() {
        ArrayList list = new ArrayList(10);
        testEnsureCapacity1(list);
        list.ensureCapacity(20);
        testEnsureCapacity1(list);
        list.ensureCapacity(15);
        testEnsureCapacity1(list);
        list.ensureCapacity(22);
        testEnsureCapacity1(list);
        list.ensureCapacity(55);
        testEnsureCapacity1(list);
    }

    private static void testEnsureCapacity1(ArrayList list) {
        Class clazz = list.getClass();
        try {
            Field elementData = clazz.getDeclaredField("elementData");
            elementData.setAccessible(true);
            Object[] o = (Object[]) elementData.get(list);
            System.out.println(o.length);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void testException() {
        List<String> list = Arrays.asList("hello", "world");
        Object[] array = list.toArray();
        array[0] = new Object();
    }

    private static void testOverFlow() {
        int oldCapacity = Integer.MAX_VALUE - 16;
        System.out.println(oldCapacity);
        int minCapacity = Integer.MAX_VALUE - 15;
        int maxSize = Integer.MAX_VALUE - 8;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - maxSize > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        System.out.println(newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > Integer.MAX_VALUE - 8) ?
                Integer.MAX_VALUE :
                Integer.MAX_VALUE - 8;
    }

}
