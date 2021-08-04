package com.morton.juc.c_026_00_interview;

import java.util.concurrent.Exchanger;

/**
 * @author MortonShaw
 * @date 2021/8/3 20:03
 */
public class T12_00_Exchanger_Not_Work {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                System.out.print(c);
                try {
                    exchanger.exchange("T1");
                    // 延迟一会，保证另一个线程先执行
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (char c : aC) {
                try {
                    exchanger.exchange("T2");
                    System.out.print(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
