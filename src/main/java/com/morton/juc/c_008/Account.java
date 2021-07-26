package com.morton.juc.c_008;

import java.util.concurrent.TimeUnit;

/**
 * @author MortonShaw
 * @date 2021/7/26 20:51
 */
public class Account {

    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(() -> {
            account.set("zhangsan", 100);
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
    }

}
