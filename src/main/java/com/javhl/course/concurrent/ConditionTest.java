package com.javhl.course.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public void producer(){

        try {

            lock.lock();

            System.out.println("生产者获取锁...");

            condition.signal();

            System.out.println("生产者唤醒消费者...");

        }finally {

            lock.unlock();
            System.out.println("生产者释放锁...");
        }
    }

    public void consumer(){

        try {

            lock.lock();

            System.out.println("消费者获取锁...");

            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("消费者开始消费...");

        }finally {

            lock.unlock();
            System.out.println("消费者释放锁...");
        }
    }

    public static void main(String[] args){

        ConditionTest conditionTest = new ConditionTest();

        new Thread(()->{conditionTest.consumer();}).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{conditionTest.producer();}).start();
    }

}
