package com.morton.juc.c_024_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * @author MortonShaw
 * @date 2021/7/31 10:55
 */
public class TicketSeller1 {

    static final List<String> tickets = new ArrayList<>();
    static Lock lock = new ReentrantLock();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                while (tickets.size() > 0) {
                    System.out.println("销售了--" + tickets.remove(0));
                }
                lock.unlock();
            }).start();
        }
    }

}
