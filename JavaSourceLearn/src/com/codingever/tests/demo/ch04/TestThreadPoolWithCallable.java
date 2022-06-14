package com.codingever.tests.demo.ch04;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*创建一个线程池，线程池中线程数量固定为6，向线程池中提交6个任务，每个人物都是计算1-10之和，最后将这6个任务的计算结果打印出来。*/
public class TestThreadPoolWithCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(6);
        ArrayList<Future<Integer>> futurList = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 6; i++) {
            Future<Integer> result = pool.submit(() -> {
                int sum = 0;
                for (int j = 0; j <= 10; j++){
                    sum += j;
                }
                return sum;
            });
            futurList.add(result);
        }
        for (Future<Integer> future : futurList){
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}
