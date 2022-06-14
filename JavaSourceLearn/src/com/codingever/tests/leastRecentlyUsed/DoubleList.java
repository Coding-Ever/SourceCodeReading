package com.codingever.tests.leastRecentlyUsed;

// 构建双链表
public class DoubleList {
    // 头尾虚节点
    private Node head, tail;
    // 链表元素数量
    private int size;

    // 双链表构造器
    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表尾部添加节点node，时间复杂度为O(1)
    public void addLast(Node node){
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    // 删除链表中的node节点，时间复杂度为O(1)
    public void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.prev = null;
        node.next = null;
        size--;
    }

    // 删除链表中第一个节点，并返回该节点，时间复杂度为O(1)
    public Node removeFirst(){
        if (head.next == tail) return null;
        Node first = head.next;
        // 删除第一个节点
        remove(first);

        return first;
    }

    // 返回链表长度
    public int size(){
        return size;
    }
}
