package com.morton.juc.c_026_00_interview;

import java.util.concurrent.locks.LockSupport;

/**
 * Locksupport park 当前线程阻塞（停止）
 * unpark(Thread t)
 *
 * @author MortonShaw
 * @date 2021/8/3 18:57
 */
public class T02_00_LockSupport {

    static Thread t1, t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t2 = new Thread(() -> {
            for (char c : aC) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }

}
