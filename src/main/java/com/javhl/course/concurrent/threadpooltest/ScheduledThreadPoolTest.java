package com.javhl.course.concurrent.threadpooltest;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

    /**
     * 以固定的频率调度，包括任务执行的时间
     */
    public static void scheduledAtFixRate(){

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        //任务开始执行的延迟时间
        int initDelay = 1;

        //任务循环执行的间隔
        int period = 1;

        scheduledExecutorService.scheduleAtFixedRate(new Task(),initDelay,period,TimeUnit.SECONDS);
    }

    /**
     * 以固定的时间间隔调度，不包括任务执行的时间
     */
    public static void scheduledWithFixDelay(){

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        //任务开始执行的延迟时间
        int initDelay = 1;

        //任务循环执行的间隔
        int period = 5;

        scheduledExecutorService.scheduleWithFixedDelay(new Task(),initDelay,period,TimeUnit.SECONDS);
    }

    static class Task implements Runnable{

        @Override
        public void run() {

            System.out.println(String.format("开始执行任务调度,执行线程:%s",Thread.currentThread().getName()));

            try {
                //模拟任务的执行时间为5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("执行任务调度完成");
        }
    }

    public static void main(String[] args){

//        ScheduledThreadPoolTest.scheduledAtFixRate();

        ScheduledThreadPoolTest.scheduledWithFixDelay();

    }
}
