package com.javhl.course;

/**
 * @Description 指令重排
 */
public class CpuReOrder {

    private int a = 0;
    private boolean flag = false;
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

        final CpuReOrder cpuReOrder = new CpuReOrder();
        //写线程
        new Thread(()->cpuReOrder.write()).start();
        //读线程
        new Thread(()->cpuReOrder.read()).start();
    }
}