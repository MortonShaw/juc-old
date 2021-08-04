package com.morton.juc.c_026_00_interview;

/**
 * 如果我想保证t2在t1之前打印，也就是说保证首先输出的是A而不是1，这个时候该如何做？
 *
 * @author MortonShaw
 * @date 2021/8/3 19:23
 */
public class T06_00_sync_wait_notify {

    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
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
