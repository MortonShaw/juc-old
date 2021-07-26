package com.morton.juc.c_011;

import java.util.concurrent.TimeUnit;

/**
 * @author MortonShaw
 * @date 2021/7/26 21:37
 */
public class T {

    int count = 0;
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                try {
                    int i = 1 / 0;
                } catch (Exception e) {
                    System.out.println("-------------------------------------");
                }
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "m1").start();
        new Thread(t::m, "m2").start();
    }

}
