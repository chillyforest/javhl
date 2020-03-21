package com.javhl.course.concurrent.threadtest;

public class ThreadYieldTest {

    public static void main(String[] args){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<10;i++){

                    System.out.println(String.format("before yield time = %s",System.nanoTime()));

                    Thread.yield();

                    System.out.println(String.format("after yield time = %s",System.nanoTime()));

                    System.out.println(String.format("线程名称:[%s],执行第:[%s]次循环",Thread.currentThread().getName(),i+1));

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        },"thread1");

        thread1.start();

        System.out.println("main thread finished successfully");

    }
}
