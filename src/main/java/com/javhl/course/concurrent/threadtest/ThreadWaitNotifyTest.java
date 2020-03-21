package com.javhl.course.concurrent.threadtest;

public class ThreadWaitNotifyTest {

    private Object monitor = new Object();

    private int data = 0;

    public void produce(){

        while (true){

            synchronized (monitor) {

                data++;

                monitor.notify();

                System.out.println(String.format("生产者线程:[%s]生产数据:[%s]",Thread.currentThread().getName(), data));

                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void consume(){

        while (true) {

            synchronized (monitor) {

                monitor.notify();

                System.out.println(String.format("消费者线程:[%s]消费数据:[%s]", Thread.currentThread().getName(), data));

                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public static void main(String[] args){

        ThreadWaitNotifyTest threadWaitNotifyTest = new ThreadWaitNotifyTest();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {

                threadWaitNotifyTest.produce();
            }

        },"producer");

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {

                threadWaitNotifyTest.consume();
            }

        },"consumer");

        producer.start();

        consumer.start();

        System.out.println("main thread finished successfully");

    }
}
