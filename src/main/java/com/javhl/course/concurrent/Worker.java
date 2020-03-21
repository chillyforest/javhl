package com.javhl.course.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class Worker {

    //此处必须用volatile修饰，否则可能会有并发问题
    private volatile boolean flag = true;

    public void startWork(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (flag){

                    doSomething();
                }
            }

        },"thread1").start();
    }

    public void stopWork(){

        flag = false;
    }

    /**
     * 具体业务方法
     */
    private void doSomething(){

    }

    public static void main(String[] args){

        Worker test = new Worker();

        test.startWork();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.stopWork();
    }
}
