package com.codingever.tests.demo.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // 原始数据
        CopyOnWriteArrayList<Integer> taskList = new CopyOnWriteArrayList<>();
        taskList.add(1);
        taskList.add(2);
        taskList.add(3);
        taskList.add(4);

        // 结果集
        List<Character> resultList = new ArrayList<>();
        //线程池，可容纳四个线程
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture[] cfs = taskList.stream()
                // 第一阶段
                .map(integer -> CompletableFuture.supplyAsync(
                        () -> calcASCII(integer), executorService)
                // 第二阶段
                .thenApply(i -> {
                    char c = (char) i.intValue();
                    System.out.println("【阶段2】线程" + Thread.currentThread().getName() + "执行完毕，"
                     + "已将int" + i + "转化为了字符" + c);
                    return c;
                })
                // 第三阶段
                .whenComplete((ch, e) -> {
                    resultList.add(ch);
                    System.out.println("【阶段3】线程" + Thread.currentThread().getName() + "执行完毕，"
                            + "已将" + ch + "增加到结果集" + resultList + "中");
                    executorService.shutdown();
                })
                ).toArray(CompletableFuture[]::new);
    }

    public static Integer calcASCII(Integer i){
        try {
            if (i == 1){
                Thread.sleep(5000);
            }else{
                Thread.sleep(1000);
            }
            i = i + 64;
            System.out.println("【阶段1】线程" + Thread.currentThread().getName()
                + "执行完毕，" + "已将" + i
                + "转化为了A(或B或C或D)对应的ASCII" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
