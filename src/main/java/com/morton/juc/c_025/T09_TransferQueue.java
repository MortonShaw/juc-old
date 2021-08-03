package com.morton.juc.c_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author MortonShaw
 * @date 2021/8/2 20:45
 */
public class T09_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("bbb");
    }

}
