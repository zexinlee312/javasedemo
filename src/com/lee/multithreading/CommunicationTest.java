package com.lee.multithreading;

/**
 * 通过生产者、消费者问题来理解线程通信
 */
public class CommunicationTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Thread productorThread = new Thread(new Producer(clerk));
        Thread consumerThread = new Thread(new Consumer(clerk));
        productorThread.start();
        consumerThread.start();

    }
}

class Clerk {
    private int productNumber = 0;
    public synchronized void addProduct() {
        if (productNumber >= 20) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("停止生产");
            return;
        } else {
            productNumber++;
            System.out.println("当前有" + productNumber + "个产品");
//            notifyAll();
        }
    }

    public synchronized void getProduct() {
        if (productNumber <= 0) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("库存不足");
            return;
        } else {
            System.out.println("消费者取走了第" + productNumber + "个产品");
            productNumber--;
            notifyAll();
        }
    }
}

class Producer implements Runnable {
    Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产");
        while (true) {
            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}

class Consumer implements Runnable { // 消费者
    Clerk clerk;
    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }
    public void run() {
        System.out.println("消费者开始取走产品");
        while (true) {
            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.getProduct();
        }
    }
}