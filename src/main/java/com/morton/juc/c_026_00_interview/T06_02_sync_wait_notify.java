package com.morton.juc.c_026_00_interview;

import java.util.concurrent.CountDownLatch;

/**
 * 如果我想保证t2在t1之前打印，也就是说保证首先输出的是A而不是1，这个时候该如何做？
 *
 * @author MortonShaw
 * @date 2021/8/3 19:23
 */
public class T06_02_sync_wait_notify {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char c : aI) {
                    try {
                        System.out.print(c);
                        o.notify();
                        o.wait(); // 让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    try {
                        latch.countDown();
                        System.out.print(c);
                        o.notify();
                        o.wait(); // 让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
    }

}
