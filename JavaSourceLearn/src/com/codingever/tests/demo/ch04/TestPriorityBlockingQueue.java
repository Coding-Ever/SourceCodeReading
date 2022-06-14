package com.codingever.tests.demo.ch04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class TestPriorityBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<MyJob> blockingQueue = new PriorityBlockingQueue<>();
        blockingQueue.add(new MyJob(3));
        blockingQueue.add(new MyJob(2));
        blockingQueue.add(new MyJob(1));

        System.out.println(blockingQueue.toString());

        System.out.println(blockingQueue.take().getId());

        System.out.println(blockingQueue.toString());

    }
}
