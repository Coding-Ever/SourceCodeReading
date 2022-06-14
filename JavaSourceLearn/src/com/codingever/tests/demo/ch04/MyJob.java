package com.codingever.tests.demo.ch04;

public class MyJob implements Comparable<MyJob>{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyJob(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(MyJob o) {
        return this.id > o.id ? 1 : (this.id < o.id ? -1 : 0);
    }

    @Override
    public String toString() {
        return "MyJob{" +
                "id=" + id +
                '}';
    }
}
