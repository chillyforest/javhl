package com.javhl.course;

public class Test extends Thread {

    private static boolean flag = false;

    public void run() {
        int i = 1;
        while (!flag) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(i++);
        }
    }

    public static void main(String[] args) throws Exception {
        new Test().start();
        Thread.sleep(2000);
        flag = true;
    }
}
