package com.javhl.course.concurrent.threadtest;

public class ThreadDamonTest {

    public static void damonThreadTest(){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                int i=0;

                while (true){

                    System.out.println(String.format("线程名称:[%s],执行第:[%s]次循环",Thread.currentThread().getName(),i+1));

                    i++;

                    try {
                        Thread.sleep(100);//状态会被清理，循环不会终止
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(String.format("[%s] is interrupted",Thread.currentThread().getName()));

                    }
                }

            }

        },"thread1");

        //设置为守护线程
        thread1.setDaemon(true);

        thread1.start();

    }

    public static void main(String[] args){

        damonThreadTest();

        System.out.println("main thread finished successfully");

    }
}
