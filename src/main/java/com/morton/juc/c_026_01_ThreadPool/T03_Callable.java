package com.morton.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

/**
 * 认识Callable，对Runnable进行了扩展
 * 对Callable的调用，可以有返回值
 *
 * @author MortonShaw
 * @date 2021/8/19 22:38
 */
public class T03_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        // 异步
        Future<String> submit = service.submit(callable);
        // 阻塞
        System.out.println(submit.get());
        service.shutdown();
    }

}
