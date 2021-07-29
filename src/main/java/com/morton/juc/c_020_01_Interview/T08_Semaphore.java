package com.morton.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * <p>
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * <p>
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * <p>
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * <p>
 * 使用Latch（门闩）替代wait notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，当count的值为零时当前线程继续运行
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这时应该考虑countdownlatch/cyclicbarrier/semaphore
 *
 * @author MortonShaw
 * @date 2021/7/28 22:32
 */
public class T08_Semaphore {

    List<Object> lists = new ArrayList<>();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    Thread t1 = null;
    Thread t2 = null;

    public static void main(String[] args) {
        T08_Semaphore t = new T08_Semaphore();
        Semaphore s = new Semaphore(1);

        t.t1 = new Thread(() -> {
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 启动");
            for (int i = 0; i < 5; i++) {
                t.add(new Object());
                System.out.println("add " + i);
            }

            s.release();
            t.t2.start();
            try {
                t.t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 5; i < 10; i++) {
                t.add(new Object());
                System.out.println("add " + i);
            }
            s.release();

            System.out.println("t1 结束");
        }, "t1");

        t.t2 = new Thread(() -> {
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 启动");

            s.release();
            System.out.println("t2 结束");
        }, "t2");

        t.t1.start();
    }

}
