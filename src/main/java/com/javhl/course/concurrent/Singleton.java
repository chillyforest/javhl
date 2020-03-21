package com.javhl.course.concurrent;

/**
 * double-check单例模式
 */
public class Singleton {

    //此处必须用volatile修饰，否则可能会有并发问题
    private volatile static Singleton singleton;

    private Singleton(){}

    public static Singleton getSingleton(){

        if(null == singleton){

            synchronized (Singleton.class){

                if(null == singleton){

                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }
}
