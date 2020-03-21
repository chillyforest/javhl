package com.javhl.course.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    /**
     * 计数器，初始为0
     */
    private Integer count = 0;

    public Integer getCount(){

        return count;
    }

    /**
     * 执行+1操作
     */
    public void add(){

        count++;
    }

    public static void main(String[] args){

        CountDownLatchTest test = new CountDownLatchTest();

        // 线程个数
        int threadCount = 3;

        //初始化工作线程的个数，并用CountDownLatch管理
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++) {

            new Thread(() -> {

                test.add();

                countDownLatch.countDown();

            }).start();
        }

        try {
            //等待所有线程执行完毕，在所有线程都执行完毕之前主线程会阻塞
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.getCount());

        //新启动一个线程进行等待
        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(test.getCount());

        }).start();

    }
}
