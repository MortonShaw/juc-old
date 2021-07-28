package com.morton.juc.c_020;

import java.util.concurrent.CountDownLatch;

/**
 * @author MortonShaw
 * @date 2021/7/27 23:07
 */
public class T06_TestCountDownLatch {

    public static void main(String[] args) {
        usingJoin();
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 100000000; j++) {
                    result += j;
                }
                latch.countDown();
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end latch " + (end - start));
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 100000000; j++) {
                    result += j;
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("end join " + (end - start));
    }


}
