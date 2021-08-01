package com.morton.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.locks.LockSupport;

/**
 * @author MortonShaw
 * @date 2021/7/30 21:27
 */
public class T01_NormalReference {

    public static void main(String[] args) {
        M m = new M();
        m = null;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            new M();
        }
        System.gc();
        System.out.println("-----------------------");
        LockSupport.park();
    }

}
