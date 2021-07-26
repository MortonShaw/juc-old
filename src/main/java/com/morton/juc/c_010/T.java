package com.morton.juc.c_010;

import java.util.concurrent.TimeUnit;

/**
 * @author MortonShaw
 * @date 2021/7/26 21:08
 */
public class T {

    synchronized void m() {
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }

}

class TT extends T {

    @Override
    synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }

}
