package com.javhl.course.demo;

import java.util.concurrent.Callable;

public class Task implements Callable {

    private Integer time = 100;

    private String name;

    public Task(String name,Integer time){

        this.name = name;

        this.time = time;
    }

    @Override
    public Object call() throws Exception{

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("name = "+name+", time="+time+" ms");

        return time+111;
    }

}
