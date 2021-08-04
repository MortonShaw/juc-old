package com.morton.juc.c_026_00_interview;

import java.util.concurrent.*;

/**
 * @author MortonShaw
 * @date 2021/8/3 22:36
 */
public class T04_01_BlockingQueue_Not_Work {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        BlockingQueue<Character> queue = new LinkedBlockingQueue<>(1);

        new Thread(() -> {
            for (char c : aI) {
                try {
                    queue.put(c);
                    Thread.sleep(10);
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
                    Thread.sleep(10);
                    queue.put(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
