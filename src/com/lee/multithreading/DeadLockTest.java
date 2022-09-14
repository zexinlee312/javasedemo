package com.lee.multithreading;

public class DeadLockTest {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread() {
            @Override
            public void run() {
                synchronized (o1) {
                    System.out.println("获取到o1锁");
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2) {
                        System.out.println("获取到o2锁");

                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                synchronized (o2) {
                    System.out.println("获取到o2锁");
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {
                        System.out.println("获取到o2锁");

                    }
                }
            }
        }.start();
    }
}
