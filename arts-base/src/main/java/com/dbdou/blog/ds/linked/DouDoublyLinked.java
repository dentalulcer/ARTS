package com.dbdou.blog.ds.linked;

/**
 * 双向链表的插入、删除、查找操作
 */
public class DouDoublyLinked<T> {

    private int size;

    private DouNode<T> head;

    private DouNode<T> tail;

    public DouDoublyLinked() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public DouDoublyLinked(DouNode<T> head, DouNode<T> tail) {
        this.size = 0;
        this.head = head;
        this.tail = tail;
    }

    /**
     * 根据索引查找值
     *
     * @param index
     * @return
     */
    public T get(int index) {
        return getNode(index).data;
    }

    /**
     * 根据索引查找节点
     *
     * @param index
     * @return
     */
    public DouNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("fuck!");
        }
        DouNode<T> node;
        if (index > size/2) {
            node = tail;
            for (int i = size-1; i > index; i--) {
                node = node.pre;
            }
        } else {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    /**
     * 根据值查找节点
     *
     * @param data
     * @return
     */
    public DouNode<T> findNode(T data) {
        DouNode<T> node = head;
        while (node != null) {
            if (node.data.equals(data)) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    /**
     * 插入到尾节点（顺序插入）
     *
     * @param data
     */
    public void add(T data) {
        if (head == null) {
            head = tail = new DouNode<>(data);
        } else {
            tail.next = new DouNode<>(data, tail, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * 插入到头节点
     *
     * @param data
     */
    public void addHead(T data) {
        if (head == null) {
            head = tail = new DouNode<>(data);
        } else {
            head = new DouNode<>(data, null, head);
            head.next.pre = head;
        }
        size++;
    }

    /**
     * 插到索引位置
     *
     * @param data
     * @param index
     */
    public void add(T data, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("fuck!");
        }
        if (index == 0) {
            addHead(data);
        } else if (index == size) {
            add(data);
        } else {
            int i = 1;
            DouNode<T> node = head.next;
            while (node != null) {
                if (i++ == index) {
                    DouNode<T> newNode = new DouNode<>(data, node.pre, node);
                    node.pre.next = newNode;
                    node.pre = newNode;
                    size++;
                    break;
                }
                node = node.next;
            }
        }
    }

    /**
     * 插入节点位置
     *
     * @param data
     * @param tarNode
     */
    public void insert(T data, DouNode<T> tarNode) {
        if (head == null || head == tarNode) {
            addHead(data);
        } else {
            DouNode<T> node = head.next;
            while (node != null) {
                if (node == tarNode) {
                    node.pre.next = new DouNode<>(data, node.pre, node);
                    node.pre = node.pre.next;
                    size++;
                    break;
                }
                node = node.next;
            }
        }
    }

    /**
     * 按索引删除
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 0 || index >= size || size == 0) {
            throw new IllegalArgumentException("fuck!");
        }
        if (index == 0) {
            if (head.next != null) {
                head.next.pre = null;
            }
            head = head.next;
            size--;
        } else if (index == (size-1)) {
            if (tail.pre != null) {
                tail.pre.next = null;
            }
            tail = tail.pre;
            size--;
        } else {
            int i = 0;
            DouNode<T> node = head;
            while (node != null) {
                if (i++ == index) {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                    size--;
                    break;
                }
                node = node.next;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Doubly Linked elements: [");
        DouNode<T> node = head;
        int i = 0;
        while (node != null) {
            if (i++ > 0) {
                builder.append(",");
            }
            builder.append("[(")
                    .append(node.pre == null ? "null" : node.pre.data)
                    .append(")")
                    .append(node.data)
                    .append("(")
                    .append(node.next == null ? "null" : node.next.data)
                    .append(")");
            node = node.next;
        }
        builder.append("], size: ").append(size);
        if (size > 0) {
            builder.append(", head: ").append(head.data)
                    .append(", tail: ").append(tail.data);
        }
        return builder.toString();
    }

    private static class DouNode<T> {

        private T data;

        private DouNode<T> pre;

        private DouNode<T> next;

        DouNode(T data) {
            this(data, null, null);
        }

        DouNode(T data, DouNode<T> pre, DouNode<T> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

    }

    public static void main(String[] args) {

        DouDoublyLinked<String> list = new DouDoublyLinked<>();
        System.out.println(list);

        list.add("head");
        System.out.println(list);

        list.add("body1");
        System.out.println(list);

        list.add("body2");
        System.out.println(list);

        list.addHead("new head");
        System.out.println(list);

        list.add("body 0", 2);
        System.out.println(list);

        list.insert("body1.5", list.tail);
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        list.remove(4);
        System.out.println(list);
    }

}
