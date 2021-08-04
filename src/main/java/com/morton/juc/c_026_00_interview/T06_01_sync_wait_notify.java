package com.morton.juc.c_026_00_interview;

/**
 * 如果我想保证t2在t1之前打印，也就是说保证首先输出的是A而不是1，这个时候该如何做？
 *
 * @author MortonShaw
 * @date 2021/8/3 19:23
 */
public class T06_01_sync_wait_notify {

    private static /*volatile*/ boolean t2Started = false;

    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                if (!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
                        t2Started = true;
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
