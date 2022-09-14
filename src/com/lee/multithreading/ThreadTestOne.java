package com.lee.multithreading;

/**
 * 本demo用于调试创建线程的两种方式
 * 一、继承Thread
 * 二、实现Runnable
 */
public class ThreadTestOne {
    public static void main(String[] args) throws InterruptedException {
        ThreadOne threadOne = new ThreadOne();
        threadOne.setName("线程一");
        threadOne.start();
        threadOne.join();
        Thread threadTwo = new Thread(new ThreadTwo());
        threadTwo.setName("线程二");
        threadTwo.start();
    }
}

class ThreadOne extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 200; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}

class ThreadTwo implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
