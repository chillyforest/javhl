package com.javhl.course.concurrent.threadtest;

import java.util.Map;

public class ThreadStopTest {

    public static void main(String[] args){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                int i = 0;

                while (i++<20){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(String.format("线程名称:[%s],当前时间:[%s]",Thread.currentThread().getName(),System.currentTimeMillis()));
                }
            }

        },"thread1");

        thread1.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //不建议使用了，要停止线程建议用标志位
        thread1.stop();

        Map<Thread,StackTraceElement[]> threadThreadTraceElementMap = Thread.getAllStackTraces();

        for(Map.Entry<Thread,StackTraceElement[]> entry : threadThreadTraceElementMap.entrySet()){

            System.out.println(entry.getKey().getName());
        }
    }
}
