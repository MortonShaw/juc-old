package com.morton.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 认识submit方法，扩展了execute方法，具有一个返回值
 *
 * @author MortonShaw
 * @date 2021/8/19 22:36
 */
public class T02_ExecutorService {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit()
    }

}
