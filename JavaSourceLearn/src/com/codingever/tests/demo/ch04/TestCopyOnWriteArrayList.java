package com.codingever.tests.demo.ch04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
/*CopyOnWrite容器（包括CopyOnWriteArrayList和CopyOnWriteSet）：当遇到写操作（即增删改）时，就会将容器自身复制一份。
* 以增加为例，当向一个CopyOnWrite容器进行增加元素时：
*       1、先将当前容器复制一份，然后向新的容器（复制后的容器）中添加元素（并不会直接向当前容器中添加元素）
*       2、增加完元素后，再将引用指向新的容器，原容器等待被GC回收。
* CopyOnWrite利用冗余实现了读写分离，在原容器中处理读请求，在新容器中处理写请求。
* 应用场景：适用于“读多写少”的业务，对于写多读少的业务不适用，因为容器的复制必加消耗性能。
* */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> names = new CopyOnWriteArrayList<>();
        names.add("zs");
        names.add("ls");
        names.add("ww");
        Iterator<String> itr = names.iterator();
        while (itr.hasNext()){
            System.out.print (itr.next() + "***");
            names.add("x");
        }
        // 输出： zs***ls***ww***
        System.out.println();
        Iterator<String> itr1 = names.iterator();
        while (itr1.hasNext()){
            System.out.print(itr1.next() + "***");
        }
        // 输出： zs***ls***ww***x***x***x***
    }
}
