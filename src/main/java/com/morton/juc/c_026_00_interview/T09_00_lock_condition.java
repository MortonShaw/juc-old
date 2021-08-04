package com.morton.juc.c_026_00_interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition本质是锁资源上不同的等待队列
 *
 * @author MortonShaw
 * @date 2021/8/3 19:48
 */
public class T09_00_lock_condition {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : aI) {
                    System.out.print(c);
                    conditionT2.signal();
                    conditionT1.await(); // 释放当前锁
                }
                conditionT2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : aC) {
                    System.out.print(c);
                    conditionT1.signal();
                    conditionT2.await(); // 释放当前锁
                }
                conditionT1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }

}
