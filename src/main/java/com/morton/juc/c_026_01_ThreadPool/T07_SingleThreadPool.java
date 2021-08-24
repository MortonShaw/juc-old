package com.morton.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MortonShaw
 * @date 2021/8/23 21:51
 */
public class T07_SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println(finalI + " " + Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }

}
