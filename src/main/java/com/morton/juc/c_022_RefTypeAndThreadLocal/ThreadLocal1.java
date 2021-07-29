package com.morton.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @author MortonShaw
 * @date 2021/7/29 23:36
 */
public class ThreadLocal1 {

    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }

}
