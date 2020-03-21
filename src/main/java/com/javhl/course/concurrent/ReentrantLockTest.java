package com.javhl.course.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private Integer count = 0;

    public Integer getCount(){

        return count;
    }

    static ReentrantLock lock = new ReentrantLock();

    public void add(){

        try{

            lock.lock();//验证重入性

            add0();

        }finally {

            lock.unlock();
        }
    }

    private void add0(){

        try{

            lock.lock();

            count++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }finally {

            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //以中断的方式获取锁
        Thread thread1 = new Thread(()->{

            try {

                lock.lockInterruptibly();

                System.out.println("thread1 get lock success");

                Thread.sleep(10000);


            } catch (InterruptedException e) {

                e.printStackTrace();

            }finally {

                lock.unlock();
            }

        });

        thread1.start();


        Thread.sleep(100);

        Thread thread2 = new Thread(()->{

            try {

                lock.lock();

                System.out.println("thread2 get lock success");

                Thread.sleep(10000);


            } catch (InterruptedException e) {

                System.out.println("thread2 cancel get lock");

            }finally {

                lock.unlock();
            }

        });

        thread2.start();


        thread2.interrupt();


//        ReentrantLockTest test = new ReentrantLockTest();
//
//        for(int i=0;i<10;i++){
//
//            new Thread(()->{
//
//                test.add();
//
//            }).start();
//        }
//
//        while (Thread.activeCount()>1){
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println(test.getCount());
    }
}
