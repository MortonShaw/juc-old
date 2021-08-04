package com.morton.juc.c_026_00_interview;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author MortonShaw
 * @date 2021/8/3 22:36
 */
public class T13_TransferQueue {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        TransferQueue<Character> transferQueue = new LinkedTransferQueue<>();

        new Thread(() -> {
            for (char c : aI) {
                try {
                    transferQueue.transfer(c);
                    System.out.print(transferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (char c : aC) {
                try {
                    System.out.print(transferQueue.take());
                    transferQueue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
