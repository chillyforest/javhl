package com.javhl.course;

public class VolatileTest01 extends Thread {

    private static boolean flag = false;

    public void run() {
        while (!flag) ;
    }

    public static void main(String[] args) throws Exception {
        new VolatileTest01().start();
        Thread.sleep(2000);
        flag = true;
    }
}
