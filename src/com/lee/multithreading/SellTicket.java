package com.lee.multithreading;

/**
 * 经典卖票问题
 */
public class SellTicket {
    public static void main(String[] args) {
        TicketWindow window = new TicketWindow();
        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class TicketWindow implements Runnable {
    public static int ticket = 100;

    // 同步方式一：同步方法，非静态方法锁是对象，静态方法锁是类
    @Override
    public synchronized void run() {
        while (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "售出一张票，票号为：" + (ticket));
            ticket--;
        }
        System.out.println("售罄~");
    }
}
