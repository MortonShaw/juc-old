package com.morton.juc.c_000;

/**
 * @author MortonShaw
 * @date 2021/7/17 8:51
 */
public class T02_HowToCreateThread {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("hello mythread");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("hello myrun");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> {
            System.out.println("hello lambda");
        }).start();
    }

}
