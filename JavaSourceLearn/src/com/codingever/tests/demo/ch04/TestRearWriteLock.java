package com.codingever.tests.demo.ch04;

import java.util.concurrent.locks.ReentrantReadWriteLock;
/*使用ReadWriteLock可以分别对读操作和写操作进行加锁
ReadWriteLock源码：
        public interface ReadWriteLock {
             //Returns the lock used for reading.
             //@return the lock used for reading
            Lock readLock();

             //Returns the lock used for writing.
             //@return the lock used for writing
            Lock writeLock();
        }
源码中readLock()和writeLock()的含义：
    readLock()：读锁，属于共享锁。加了读锁的资源，可以在没有写锁的时候被多个线程共享。
                若线程t1获得读锁的情况下，如果线程t2要申请写锁，则t2会被堵塞等待t1释放读锁；如果线程t2要申请读锁，则t2可以直接访问读锁。
    writeLock()：写锁，属于独占锁。加了写锁的资源，不能再被其他线程读或写。
                也就是说如果线程t1获得写锁的情况下，不论线程t2要申请写锁还是读锁，t2都会被堵塞等待，直到t1释放写锁。
* */
public class TestRearWriteLock {
    // 读写锁
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        TestRearWriteLock test = new TestRearWriteLock();
        // 线程t1
        new Thread(() -> {
            // 读操作
            test.myRead(Thread.currentThread());
            // 写操作
            test.myWrite(Thread.currentThread());
        }, "t1").start();
        // 线程t2
        new Thread(() -> {
            // 读操作
            test.myRead(Thread.currentThread());
            // 写操作
            test.myWrite(Thread.currentThread());
        }, "t2").start();
    }

    // 用读锁来获取读操作
    public void myRead(Thread thread){
        rwl.readLock().lock();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完成！*******************");
        } finally {
            rwl.readLock().unlock();
        }
    }

    // 用读锁来获取读操作
    public void myWrite(Thread thread){
        rwl.writeLock().lock();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println(thread.getName() + "正在进行写操作");
            }
            System.out.println(thread.getName() + "写操作完成！#######################");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}


