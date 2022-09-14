package com.lee.multithreading;

/**
 * 存款取款的例子，多线程不同步会破坏数据一致性
 */
public class DepositMoney {
    public static void main(String[] args) throws InterruptedException {
        Husband jack = new Husband("Jack");
        Wife ross = new Wife("Ross");
        new Thread(jack).start();
        new Thread(ross).start();
        Thread.sleep(1000);
        System.out.println("账户余额：" + jack.getBalance());
    }
}

class Account {
    private static int balance;

    public int getBalance() {
        return balance;
    }

    public void depositMoney(int money) {
        balance += money;
    }

    public void getMoney(int money) {
        balance -= money;
    }
}

class Husband extends Account implements Runnable {
    private String name;

    public Husband(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            this.depositMoney(2000);
            System.out.println("勤劳丈夫存了2000元");
        }
    }
}

class Wife extends Account implements Runnable {
    private String name;

    public Wife(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            this.getMoney(3000);
            System.out.println("败家娘们取了3000元");
        }
    }
}