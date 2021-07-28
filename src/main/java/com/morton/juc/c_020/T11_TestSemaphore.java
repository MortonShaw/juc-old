package com.morton.juc.c_020;

import java.util.concurrent.Semaphore;

/**
 * @author MortonShaw
 * @date 2021/7/28 20:15
 */
public class T11_TestSemaphore {

    public static void main(String[] args) {
//        Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2, true);

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }

}
