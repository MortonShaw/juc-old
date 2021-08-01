package com.morton.juc.c_022_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * @author MortonShaw
 * @date 2021/7/30 22:13
 */
public class T03_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.gc();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tl.remove();
    }

}
