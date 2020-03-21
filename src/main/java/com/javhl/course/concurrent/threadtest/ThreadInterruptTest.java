package com.javhl.course.concurrent.threadtest;

public class ThreadInterruptTest {

    public static void interruptTest(){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                int i=0;

                while (!Thread.currentThread().isInterrupted()){

                    System.out.println(String.format("线程名称:[%s],执行第:[%s]次循环",Thread.currentThread().getName(),i+1));

                    i++;

                }
            }

        },"thread1");

        thread1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();

    }

    public static void interruptWithSleepTest(){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                int i=0;

                while (!Thread.currentThread().isInterrupted()){

                    System.out.println(String.format("线程名称:[%s],执行第:[%s]次循环",Thread.currentThread().getName(),i+1));

                    i++;

                    try {
                        Thread.sleep(100);//状态会被清理，循环不会终止
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(String.format("[%s] is interrupted",Thread.currentThread().getName()));

                    }
                }

                System.out.println(String.format("线程名称:[%s],执行结束",Thread.currentThread().getName()));

            }

        },"thread1");

        thread1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();

    }

    public static void main(String[] args){

        interruptTest();

        System.out.println("main thread finished successfully");

    }
}
