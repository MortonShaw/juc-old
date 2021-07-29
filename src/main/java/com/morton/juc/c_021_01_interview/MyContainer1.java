package com.morton.juc.c_021_01_interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 *
 * @author MortonShaw
 * @date 2021/7/29 21:51
 */
public class MyContainer1<T> {

    final private List<T> list = new ArrayList<>();
    final private int MAX = 4;
    private int count = 0;

    public synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        this.notifyAll();
    }

    public synchronized T get() {
        /*
         * 必须用while，不能用if
         * 使用if时，被唤醒后，直接往下执行了，这一瞬间list可能以为空，会报空指针异常
         * 所以需要使用while，回来再检查一下是否为空
         */
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T remove = list.remove(0);
        count--;
        this.notifyAll();
        return remove;
    }

    public static void main(String[] args) {
        MyContainer1<String> t = new MyContainer1<>();
        // 消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String s = t.get();
                    System.out.println(Thread.currentThread().getName() + " -> " + s);
                }
            }, "c" + i).start();
        }

        // 生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    t.put(Thread.currentThread().getName() + " - " + j);
                }
            }, "p" + i).start();
        }
    }

}
