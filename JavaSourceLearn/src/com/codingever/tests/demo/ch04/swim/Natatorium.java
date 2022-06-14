package com.codingever.tests.demo.ch04.swim;

import java.util.concurrent.DelayQueue;

public class Natatorium implements Runnable{

    private DelayQueue<Swimmer> delayQueue = new DelayQueue<>();
    private volatile boolean isOpen = true;

    public void addSwimmer(String name, int playTime){
        long endTime = System.currentTimeMillis() + playTime * 1000 * 60;
        Swimmer swimmer = new Swimmer(name, endTime);
        System.out.println(swimmer.getName() + "进入游泳馆，可供使用时间" + playTime + "分");
        this.delayQueue.add(swimmer);
    }

    @Override
    public void run() {
        while (isOpen){
            try {
                Swimmer swimmer = delayQueue.take();
                System.out.println(swimmer.getName() + "游泳时间结束！");
                if (delayQueue.size() == 0){
                    isOpen = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
