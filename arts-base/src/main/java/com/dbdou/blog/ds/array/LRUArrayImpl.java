package com.dbdou.blog.ds.array;

import com.dbdou.blog.ds.LRUList;

/**
 * LRU 的数组实现
 */
public class LRUArrayImpl<T> implements LRUList<T> {

    private T[] data;

    private int size;

    private int length;

    @SuppressWarnings("unchecked")
    public LRUArrayImpl(int length) {
        this.size = 0;
        this.length = length;
        this.data = (T[]) new Object[length];
    }

    @Override
    public void add(T item) {
        if (size == length) {
            for (int i = 0; i < size-1; i++) {
                data[i] = data[i+1];
            }
        } else {
            size++;
        }
        data[size-1] = item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("fuck!");
        }
        T item = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i+1];
        }
        data[size-1] = item;
        return item;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, length = %d, elements = [", size, length));
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    public static void main(String[] args) {
        LRUList<String> list = new LRUArrayImpl<>(5);
        list.add("a");
        System.out.println(list);

        list.add("b");
        System.out.println(list);

        list.add("c");
        System.out.println(list);

        list.add("d");
        System.out.println(list);

        list.add("e");
        System.out.println(list);

        list.add("f");
        System.out.println(list);

        list.get(3);
        System.out.println(list);

        list.get(0);
        System.out.println(list);

        list.add("g");
        System.out.println(list);

        list.get(4);
        System.out.println(list);

    }

}
