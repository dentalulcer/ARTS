package com.dbdou.blog.ds.linked;

/**
 * 单链表的插入、删除、查找操作
 */
public class DouSinglyLinked<T> {

    /** 头节点 */
    private DouNode<T> head;

    public DouSinglyLinked() {
        this.head = null;
    }

    public DouSinglyLinked(DouNode<T> head) {
        this.head = head;
    }

    /**
     * 根据索引查找值
     *
     * @param index 索引
     * @return 目标节点的值
     */
    public T get(int index) {
        DouNode<T> node = getNode(index);
        return node == null ? null : node.data;
    }

    /**
     * 根据索引查找节点
     *
     * @param index 索引
     * @return 目标节点
     */
    public DouNode<T> getNode(int index) {
        if (index < 0 || head == null) {
            return null;
        }
        if (index == 0) {
            return head;
        }
        DouNode<T> node = head;
        while (node != null) {
            if (index-- == 0) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    /**
     * 根据值查找节点
     *
     * @param data 需要查找的值
     * @return 目标节点
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
     * @param data 需要插入的值
     */
    public void add(T data) {
        addTail(data);
    }

    /**
     * 插入到头结点
     *
     * @param data 需要插入的值
     */
    public void addHead(T data) {
        DouNode<T> newNode;
        if (head == null) {
            newNode = new DouNode<>(data, null);
        } else {
            newNode = new DouNode<>(data, head);
        }
        head = newNode;
    }

    /**
     * 插入到尾节点（顺序插入）
     *
     * @param data 需要插入的值
     */
    public void addTail(T data) {
        DouNode<T> newNode = new DouNode<>(data, null);
        if (head == null) {
            head = newNode;
        } else {
            DouNode<T> node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
    }

    /**
     * 插到索引位置
     *
     * @param data 需要插入的值
     * @param index 需要插入的索引
     */
    public void add(T data, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("fuck!");
        }

        if (index == 0) {
            addHead(data);
        } else {
            int i = 0;
            DouNode<T> node = head;
            while (node != null) {
                if (++i == index) {
                    node.next = new DouNode<>(data, node.next);
                    break;
                }
                node = node.next;
            }
        }
    }

    /**
     * 插入节点位置
     *
     * @param data 需要插入的值
     * @param tarNode 需要插入的节点
     */
    public void insert(T data, DouNode<T> tarNode) {
        if (tarNode == null) {
            throw new IllegalArgumentException("fuck!");
        }

        if (tarNode == head || head == null) {
            addHead(data);
        } else {
            DouNode<T> node = head;
            while (node.next != null) {
                if (node.next == tarNode) {
                    node.next = new DouNode<>(data, node.next);
                    break;
                }
                node = node.next;
            }
        }
    }

    /**
     * 按索引删除
     *
     * @param index 索引
     */
    public void remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("fuck!");
        }

        int i = 0;
        DouNode<T> node = head;
        while (node != null) {
            if (++i == index) {
                node.next = node.next == null ? null : node.next.next;
                break;
            }
            node = node.next;
        }
    }

    /**
     * 按值删除
     *
     * @param data 需要删除的值
     */
    public void removeElement(T data) {
        if (head == null) {
            return ;
        }
        if (head.data.equals(data)) {
            head = head.next;
        } else {
            DouNode<T> node = head;
            while (node.next != null) {
                if (node.next.data.equals(data)) {
                    node.next = node.next.next;
                    break;
                }
                node = node.next;
            }
        }
    }

    /**
     * 按节点删除
     *
     * @param tarNode 需要删除的节点
     */
    public void removeNode(DouNode<T> tarNode) {
        if (head == null) {
            return ;
        }
        if (head == tarNode) {
            head = head.next;
        } else {
            DouNode<T> node = head;
            while (node.next != null) {
                if (node.next == tarNode) {
                    node.next = node.next.next;
                    break;
                }
                node = node.next;
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Linked elements: [");
        DouNode<T> node = head;
        int i = 0;
        while (node != null) {
            if (i++ > 0) {
                builder.append(",");
            }
            builder.append(node.data);
            node = node.next;
        }
        builder.append("], size: ").append(i);
        return builder.toString();
    }

    /**
     * 单链表内的节点
     *
     * @param <T>
     */
    private static class DouNode<T> {

        /** 存储节点数据 */
        private T data;

        /** 指向下一个节点 */
        private DouNode<T> next;

        DouNode(T data, DouNode<T> next) {
            this.data = data;
            this.next = next;
        }

    }

    public static void main(String[] args) {
        DouNode<String> head = new DouNode<>("head", null);

        DouSinglyLinked<String> list = new DouSinglyLinked<>(head);
        System.out.println(list);

        list.add("body1");
        list.add("body2");
        System.out.println(list);

        list.addHead("new head");
        System.out.println(list);

        list.add("add body 2", 2);
        System.out.println(list);

        list.insert("add body 3", head.next.next);
        System.out.println(list);

        list.remove(2);
        System.out.println(list);

        list.remove(4);
        System.out.println(list);

        list.removeElement("add body 3");
        System.out.println(list);

        list.removeNode(head);
        System.out.println(list);

    }

}
