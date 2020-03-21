package com.javhl.course.concurrent.threadtest;

public class ThreadExceptionTest {

    public static void main(String[] args){

        try {

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {

                    throw new RuntimeException("exception test");
                }

            }, "thread1");

            thread1.start();

        }catch (Throwable throwable){//子线程异常不会传递到主线程

            System.out.println("thread1 run exception");
        }

        System.out.println("main thread finished successfully");

    }
}
