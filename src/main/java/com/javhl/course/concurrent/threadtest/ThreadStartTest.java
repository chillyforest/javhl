package com.javhl.course.concurrent.threadtest;

public class ThreadStartTest {

    public static void main(String[] args){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

//                while (true)
                System.out.println("1");
            }
        });

        for(int i=0;i<10;i++){

            thread.start();
        }
    }
}
