package com.morton.juc.c_020;

import java.util.concurrent.TimeUnit;

/**
 * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 *
 * @author MortonShaw
 * @date 2021/7/27 22:21
 */
public class T01_ReentrantLock1 {

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
//            if (i == 2) {
//                m2();
//            }
        }

    }

    synchronized void m2() {
        System.out.println("m2 ...");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 rl = new T01_ReentrantLock1();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(rl::m2).start();
    }


}
