package com.javhl.course.concurrent;

import lombok.Synchronized;

public class DeadLockTest {

    public static void main(String[] args) throws InterruptedException {

        Object lock1 = new Object();

        Object lock2 = new Object();

        Thread thread1 = new Thread(()->{

            synchronized(lock1){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 get lock1");

                synchronized (lock2){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("thread1 get lock2");

                }
            }

        });

        thread1.start();

        Thread thread2 = new Thread(()->{

            synchronized(lock2){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread2 get lock2");

                synchronized (lock1){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("thread2 get lock1");

                }
            }

        });

        thread2.start();


        Thread.sleep(1000);

        Thread thread3 = new Thread(()->{

            synchronized(lock1){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread3 get lock1");

                synchronized (lock2){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("thread3 get lock2");

                }
            }

        });

        thread3.start();



    }
}
