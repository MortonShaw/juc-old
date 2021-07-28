package com.morton.juc.c_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还可以指定为公平锁
 *
 * @author MortonShaw
 * @date 2021/7/27 22:55
 */
public class T05_ReentrantLock5 implements Runnable {
    private ReentrantLock lock = new ReentrantLock(true); //参数为true表示为公平锁，请对比输出结果

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 rl = new T05_ReentrantLock5();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);
        th1.start();
        th2.start();
    }


}
