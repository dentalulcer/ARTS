package com.dbdou.blog.ds.array;

/**
 * 数组的插入、删除、按照下标随机访问操作；
 */
public class DouArray<T> {

    /** 存储数据的数组 */
    private T[] data;
    /** 实际元素数量 */
    private int size;

    /** 默认数组容量:10 [ should be final, JUST FOR TEST ] */
    private static int DEFAULT_LENGTH = 10;

    /** 数组最大容量:Integer.MAX_VALUE [ should be final, JUST FOR TEST ] */
    private static int MAX_LENGTH = Integer.MAX_VALUE;

    public DouArray() {
        this(DEFAULT_LENGTH);
    }

    @SuppressWarnings("unchecked")
    public DouArray(int length) {
        data = (T[]) new Object[length];
        size = 0;
    }

    /**
     * [ JUST FOR TEST ]
     *
     * @param defaultLength 默认容量
     * @param maxLength 最大容量
     */
    @SuppressWarnings("unchecked")
    private DouArray(int defaultLength, int maxLength) {
        data = (T[]) new Object[defaultLength];
        size = 0;
        DEFAULT_LENGTH = defaultLength;
        MAX_LENGTH = maxLength;
    }

    /**
     * 获取数组大小
     *
     * @return 数组大小
     */
    public int getLength() {
        return data.length;
    }

    /**
     * 获取实际元素个数
     *
     * @return 实际元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断数组是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 修改 index 位置的元素
     *
     * @param index 下标
     * @param item 对应元素
     */
    public void set(int index, T item) {
        checkIndex(index);
        data[index] = item;
    }

    /**
     * 获取对应 index 位置的元素
     *
     * @param index 下标
     * @return 对应元素
     */
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * 查看数组是否包含元素e
     *
     * @param item 要查找的元素
     * @return 是否包含
     */
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取对应元素的下标, 未找到，返回 -1
     *
     * @param item 要查找的元素
     * @return 下标
     */
    public int find(T item) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在 index 位置，插入元素e, 时间复杂度 O(m+n)
     *
     * @param index 下标
     * @param item 插入的元素
     */
    public void add(int index, T item) {
        checkIndexForAdd(index);
        checkResizeForAdd();
        /*
         * 作用相当于：
         *
            for (int i = size - 1; i >= index; i--) {
                data[i+1] = data[i];
            }
         */
        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = item;
        size++;
    }

    /**
     * 向数组头插元素
     *
     * @param item 插入的元素
     */
    public void addHead(T item) {
        add(0, item);
    }

    /**
     * 向数组尾插元素
     *
     * @param item 插入的元素
     */
    public void addTail(T item) {
        add(size, item);
    }

    /**
     * 根据下标移除元素
     *
     * @param index 下标
     * @return 移除的元素
     */
    public T remove(int index) {
        checkIndex(index);
        T ret = data[index];
        /*
         * 作用相当于：
         *
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i+1];
            }
         */
        System.arraycopy(data, index + 1, data, index, size - 1 - index);

        size--;
        data[size] = null;

        checkResizeForRemove();
        return ret;
    }

    /**
     * 移除第一个元素
     *
     * @return 移除的元素
     */
    public T removeHead() {
        return remove(0);
    }

    /**
     * 移除最后一个元素
     *
     * @return 移除的元素
     */
    public T removeTail() {
        return remove(size - 1);
    }

    /**
     * 移除指定元素
     *
     * @param item 指定元素
     */
    public void removeElement(T item) {
        int index = find(item);
        if (index >= 0) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, length = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     * 检测是否需要扩容
     */
    private void checkResizeForAdd() {
        if (size == data.length) {
            int safeLen = 2 * data.length;
            if (safeLen < data.length || safeLen > MAX_LENGTH) {
                safeLen = MAX_LENGTH;
            }
            resize(safeLen);
        }
    }

    /**
     * 检测是否需要缩容
     */
    private void checkResizeForRemove() {
        if (size <= data.length / 4 && data.length >= DEFAULT_LENGTH * 2) {
            resize(data.length / 2);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int length) {
        T[] newData = (T[]) new Object[length];
        /*
         * 作用相当于：
             for (int i = 0; i < size; i++) {
                newData[i] = data[i];
             }
         */
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * 检测下标是否越界
     *
     * @param index 下标
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("fuck! Require index >=0 and index < size.");
        }
    }

    /**
     * 检测下标是否越界
     *
     * @param index 下标
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size || index == MAX_LENGTH) {
            throw new IllegalArgumentException("fuck! Require index >=0 and index <= size and index < MAX_LENGTH.");
        }
    }


    public static void main(String[] args) {

        DouArray<String> arr = new DouArray<>(2, 5);
        System.out.println(arr);

        arr.addTail("a");
        arr.addTail("b");
        System.out.println(arr);

        arr.addTail("c");
        System.out.println(arr);

        arr.addTail("d");
        arr.addTail("e");
        // arr.addTail("f");
        System.out.println(arr);

        arr.remove(1);
        System.out.println(arr);
        arr.removeHead();
        System.out.println(arr);
        arr.removeTail();
        arr.removeElement("d");
        System.out.println(arr);

    }

}
