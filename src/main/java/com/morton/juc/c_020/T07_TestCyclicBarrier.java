package com.morton.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author MortonShaw
 * @date 2021/7/27 23:23
 */
public class T07_TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("人满，发车"));


        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                try {
                    barrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }


}
