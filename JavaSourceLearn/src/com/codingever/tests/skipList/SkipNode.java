package com.codingever.tests.skipList;

public class SkipNode<T> {
    int key;
    T value;
    SkipNode right, down;// 向右向下两个方向的指针
    public SkipNode(int key, T value){
        this.key = key;
        this.value = value;
    }
}
