package com.javhl.course.clazzloader;

public class FatherSonClassTest {

    public String test = "fatherSonTest";

    static class Father{

        Father(){

            System.out.println("father 构造函数");
        }

        static String fatherName = "小头爸爸";

        final static String test = "final static test";

        static {

            System.out.println("father 静态代码块");
        }
    }

    static class Son extends Father{

        Son(){

            super();
            System.out.println("son 构造函数");
        }

        static String sonName = "大头儿子";

        static {

            System.out.println("son 静态代码块");
        }
    }

    public static void main(String[] args){

//        System.out.println(Son.fatherName);//1

        System.out.println(Son.sonName);//2

//        System.out.println(Son.test);//3

//        new Son();//4

    }
}
