package com.morton.juc.c_022_RefTypeAndThreadLocal;

/**
 * @author MortonShaw
 * @date 2021/7/30 21:26
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }

}
