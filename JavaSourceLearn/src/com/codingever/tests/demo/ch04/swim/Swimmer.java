package com.codingever.tests.demo.ch04.swim;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Swimmer implements Delayed {

    private String name;
    private long endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Swimmer(String name, long endTime) {
        this.name = name;
        this.endTime = endTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed delayed) {
        Swimmer swimmer = (Swimmer) delayed;
        return this.getDelay(TimeUnit.SECONDS) - swimmer.getDelay(TimeUnit.SECONDS) > 0 ? 1 : 0;
    }
}
