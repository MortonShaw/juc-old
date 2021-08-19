package com.morton.juc.c_026_00_interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MortonShaw
 * @date 2021/8/4 23:07
 */
public class T99_wait {

    Lock lock = new ReentrantLock();

    public void m() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            LockSupport.park();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        T99_wait wait = new T99_wait();
        for (int i = 0; i < 5; i++) {
            new Thread(wait::m, String.valueOf(i)).start();
        }
    }

}
