package com.dbdou.blog.ds.linked;

import com.dbdou.blog.ds.LRUList;

/**
 * LRU 链表实现
 */
public class LRUSinglyLinkedImpl<T> implements LRUList<T> {

    private Node<T> head;

    private int size;

    private int length;

    LRUSinglyLinkedImpl(int length) {
        this(length, null);
    }

    LRUSinglyLinkedImpl(int length, Node<T> head) {
        if (length < 2) {
            throw new IllegalArgumentException("fuck!");
        }
        this.size = 0;
        this.length = length;
        this.head = head;
    }

    @Override
    public void add(T item) {
        if (head == null) {
            head = new Node<>(item);
            size++;
        } else {
            Node<T> node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(item);
            if (size == length) {
                head = head.next;
            } else {
                size++;
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("fuck!");
        }
        if (size == 1) {
            return head.data;
        }
        int i = 0;
        Node<T> node = head;
        Node<T> tarNode = head;
        if (index == 0) {
            head = head.next;
            while (node.next != null) {
                node = node.next;
            }
        } else {
            while (node.next != null) {
                if (++i == index) {
                    tarNode = node.next;
                    node.next = node.next.next;
                    if (index == size - 1) {
                        break;
                    }
                }
                node = node.next;
            }
        }
        tarNode.next = null;
        node.next = tarNode;
        return node.data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Linked size = %d, length = %d, elements = [", size, length));
        Node<T> node = head;
        int i = 0;
        while (node != null) {
            if (i++ > 0) {
                builder.append(",");
            }
            builder.append(node.data);
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }

    private static class Node<T> {

        private T data;

        private Node<T> next;

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

    }

    public static void main(String[] args) {

        LRUList<String> list = new LRUSinglyLinkedImpl<>(5);
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
