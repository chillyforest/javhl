package com.javhl.course;

/**
 * @Description 线程同步的例子
 */
public class SynchronizedSample {

    private int a = 0;
    private boolean flag = false;
    public synchronized void write(){

        a = 1;          //1
        flag = true;    //2

    }
    public synchronized void read(){

        if(flag){       //3

            int i = a; //4

            System.out.println(i);
        }
    }
    public static void main(String[] args){

        final SynchronizedSample synchronizedSample = new SynchronizedSample();
        //写线程
        new Thread(()->synchronizedSample.write()).start();
        //读线程
        new Thread(()->synchronizedSample.read()).start();
    }
}


