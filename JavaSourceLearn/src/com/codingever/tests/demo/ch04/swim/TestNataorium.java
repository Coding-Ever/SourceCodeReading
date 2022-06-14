package com.codingever.tests.demo.ch04.swim;

public class TestNataorium {
    public static void main(String[] args) {
        try {
            Natatorium natatorium = new Natatorium();
            Thread thread = new Thread(natatorium);
            thread.start();
            natatorium.addSwimmer("zs", 1);
            natatorium.addSwimmer("ls", 2);
            natatorium.addSwimmer("ww", 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
