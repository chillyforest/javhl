package com.javhl.course.concurrent.threadpooltest;

import java.util.concurrent.*;

public class ThreadPoolCoreParamsTest {

    //固定个数线程池，常用，队列容量：Integer.MAX_VALUE
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static ExecutorService buildThreadPool(){

        //核心线程数
        int coreThreads = 1;

        //最大线程数
        int maxThreads = 2;

        //超时时间，单位s
        int keepaliveTime = 10;

        //阻塞队列大小
        int blockQueueSize = 30;

        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>(blockQueueSize);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreThreads,maxThreads,keepaliveTime,TimeUnit.SECONDS,blockingQueue);

        //允许核心线程超时，当任务都运行结束时，线程池的线程数为0
//        executor.allowCoreThreadTimeOut(true);

        return  executor;
    }

   static class Task implements Runnable{

        public Task(int taskId){

            this.taskId = taskId;
        }

        private int taskId;

        @Override
        public void run() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("线程: %s 执行任务: %s 完毕",Thread.currentThread().getName(),taskId));
        }
    }

    public static void main(String[] args){

        ExecutorService executorService = ThreadPoolCoreParamsTest.buildThreadPool();

        //模拟执行100个任务
        int count = 100;

        while (count>0){

            try {

                executorService.submit(new Task(count--));

            }catch (RejectedExecutionException e){//拒绝策略

                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //等待线程池的任务停止，并不会停止线程池
        try {
            executorService.awaitTermination(10*60,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //停止线程池
//        executorService.shutdown();

        System.out.println("线程池停止...");

    }
}
