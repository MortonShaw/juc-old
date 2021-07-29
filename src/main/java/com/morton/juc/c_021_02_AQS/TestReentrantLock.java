package com.morton.juc.c_021_02_AQS;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MortonShaw
 * @date 2021/7/29 22:41
 */
public class TestReentrantLock {

    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }


}
