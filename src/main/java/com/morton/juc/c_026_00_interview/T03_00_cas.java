package com.morton.juc.c_026_00_interview;

/**
 * @author MortonShaw
 * @date 2021/8/3 19:04
 */
public class T03_00_cas {

    enum ReadyToRun {
        T1, T2
    }

    static /*volatile*/ ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (r != ReadyToRun.T1) {

                }
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }).start();

        new Thread(() -> {
            for (char c : aC) {
                while (r != ReadyToRun.T2) {

                }
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }).start();
    }

}
