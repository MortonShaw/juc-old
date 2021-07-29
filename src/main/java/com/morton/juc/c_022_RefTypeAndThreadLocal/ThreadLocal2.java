package com.morton.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * <p>
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 *
 * @author MortonShaw
 * @date 2021/7/29 23:38
 */
public class ThreadLocal2 {

    static ThreadLocal<Person> t = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.set(new Person());
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }

}
