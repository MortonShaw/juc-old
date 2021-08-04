package com.morton.juc.c_026_00_interview;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author MortonShaw
 * @date 2021/8/3 22:36
 */
public class T14_SynchronusQueue {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        SynchronousQueue<Character> queue = new SynchronousQueue<>();

        new Thread(() -> {
            for (char c : aI) {
                try {
                    queue.put(c);
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (char c : aC) {
                try {
                    System.out.print(queue.take());
                    queue.put(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
