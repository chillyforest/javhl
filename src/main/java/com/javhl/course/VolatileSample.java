package com.javhl.course;

/**
 * @Description volatile的例子
 */
public class VolatileSample {

    private int a = 0;
    private volatile boolean flag = false;
    public void write(){

        a = 1;          //1
        flag = true;    //2

    }
    public void read(){

        if(flag){       //3

            int i = a; //4

            System.out.println(i);
        }
    }
    public static void main(String[] args){

        final VolatileSample volatileSample = new VolatileSample();
        //写线程
        new Thread(()->volatileSample.write()).start();
        //读线程
        new Thread(()->volatileSample.read()).start();
    }
}


