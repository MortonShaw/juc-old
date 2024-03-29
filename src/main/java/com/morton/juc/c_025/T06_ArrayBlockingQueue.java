package com.morton.juc.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author MortonShaw
 * @date 2021/8/1 16:22
 */
public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            strs.put("a" + i);
        }

//        strs.put("aaa"); //满了就会等待，程序阻塞
//        strs.add("aaa");
//        strs.offer("aaa");
        strs.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(strs);
        System.out.println(strs.size());
    }


}
