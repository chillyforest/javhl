package com.javhl.course.concurrent.threadtest;

public class ThreadCreateTest {

    public void createThreadTest1(){

        Thread myThread = new Thread(new MyRunnable());

        Thread thread = new MyThread();
    }

    class MyThread extends Thread{

        @Override
        public void run() {

            //to do something in this thread
        }
    }

    class MyRunnable implements Runnable{


        @Override
        public void run() {

            //to do something in this thread
        }
    }
}
