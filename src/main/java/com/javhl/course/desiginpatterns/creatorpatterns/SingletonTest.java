package com.javhl.course.desiginpatterns.creatorpatterns;

public class SingletonTest {

    //饿汉式单例
    private static SingletonTest ehSingleton = new SingletonTest();

    public static SingletonTest getEhSingleton(){

        return ehSingleton;
    }

    //dcl单例，懒汉式
    private static volatile SingletonTest singleton;

    private SingletonTest(){

    }

    public static SingletonTest getDclSingleton(){

        if(null == singleton){

            synchronized (SingletonTest.class){

                if(null == singleton){

                    singleton = new SingletonTest();
                }
            }
        }

        return singleton;
    }

    //静态内部类实现单例，懒汉；由类加载机制保证线程安全；相比Dcl方式，有内部类创建实例无法传参
    public static SingletonTest getStaticInnerSingleton(){

        return Helper.SINGLETON_TEST;
    }

    private static class Helper{

        private static final SingletonTest SINGLETON_TEST = new SingletonTest();
    }
}
