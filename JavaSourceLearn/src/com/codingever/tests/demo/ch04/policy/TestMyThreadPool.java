package com.codingever.tests.demo.ch04.policy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestMyThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS,
                // 使用有界队列
                new ArrayBlockingQueue<>(3),
                // 使用无界队列
                //new LinkedBlockingQueue<>(),
                // 自定义拒绝策略
                new MyRejectPolicy());
                //new ThreadPoolExecutor.AbortPolicy());
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        MyThread t3 = new MyThread("t3");
        MyThread t4 = new MyThread("t4");
        MyThread t5 = new MyThread("t5");
        MyThread t6 = new MyThread("t6");

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);

        pool.shutdown();
    }
}
