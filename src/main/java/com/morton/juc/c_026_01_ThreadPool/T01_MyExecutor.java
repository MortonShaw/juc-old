package com.morton.juc.c_026_01_ThreadPool;

import java.util.concurrent.Executor;

/**
 * 将线程定义和执行分离
 *
 * @author MortonShaw
 * @date 2021/8/19 22:30
 */
public class T01_MyExecutor implements Executor {

    public static void main(String[] args) {
        new T01_MyExecutor().execute(() -> {
            System.out.println("hello executor");
        });
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }

}
