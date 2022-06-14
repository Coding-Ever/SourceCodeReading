package com.codingever.tests.demo.ch04.myexecutor;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TestPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> result = null;
        ScheduledExecutorService schedulPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 2; i++) {
            result = schedulPool.schedule(new ThreadTask("thread" + i), (int)(Math.random()*10), TimeUnit.SECONDS);
            results.add(result);
        }

        // 打印结果
        for (Future<String> res : results){
            System.out.println(res.isDone() ? "已完成" : "未完成");
            System.out.println("等待线程执行完毕后，返回的结果：" + res.get());
        }
        schedulPool.shutdown();
    }
}
