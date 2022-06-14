package com.codingever.tests.demo.ch04.myexecutor;


import java.util.concurrent.Callable;

/*创建一个线程数量固定为本机CPU核数的线程池，实现以下需求：
* 1、 提交2个任务，每个任务依次执行以下操作：记录当前线程的启动时间，随机休眠2秒以内，打印当前线程名，返回当前线程名
* 2、 这两个任务不会立即执行，而是等待一段时间（10s以内）后再执行
* 3、 Main程序会和线程池中执行这两个任务的子线程同时执行。在这两个子线程正在执行提交任务的同时，main线程会尝试获取这两个线程的结果，
* 如果这两个子线程还没执行完则提示“未完成”，否则提示“已完成”，并打印线程的返回值。*/
public class ThreadTask implements Callable<String> {

    private String tname;

    public ThreadTask(String tname){
        this.tname = tname;
    }

    @Override
    public String call() throws Exception {
        // 获取当前线程名
        String name = Thread.currentThread().getName();
        long currentTimeMills = System.currentTimeMillis();
        System.out.println(name + "- 【" + tname + "】 启动时间： " + currentTimeMills);
        // 模拟线程执行
        Thread.sleep((long) Math.random() * 2000);
        System.out.println(name + "- 【" + tname + "】 正在执行...");
        return name + "- 【" + tname + "】";
    }
}
