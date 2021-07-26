package com.morton.juc.c_005;

/**
 * @author MortonShaw
 * @date 2021/7/17 9:59
 */
public class T implements Runnable {

    private int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }

}
