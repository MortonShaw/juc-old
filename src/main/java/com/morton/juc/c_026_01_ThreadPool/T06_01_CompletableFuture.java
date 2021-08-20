package com.morton.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 假设你能够提供一个服务
 * 这个服务查询各大电商网站同一类产品的价格并汇总展示
 *
 * @author MortonShaw
 * @date 2021/8/20 8:34
 */
public class T06_01_CompletableFuture {

    public static void main(String[] args) throws IOException {
//        long start = System.currentTimeMillis();
//        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTM);
//        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTB);
//        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfJD);
//        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
//        long end = System.currentTimeMillis();
//        System.out.println("use completable future! " + (end - start));

        CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTM)
                .thenApply(String::valueOf)
                .thenApply(str -> "price " + str)
                .thenAccept(System.out::println);
        System.in.read();
    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }

}
