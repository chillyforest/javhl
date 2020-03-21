package com.javhl.course.jvm;

/**
 * 栈溢出测试
 */
public class StackOverflow {

    private int depth = 0;

    public void test(){

        depth++;
        test();
    }

    public static void main(String[] args){

        StackOverflow stackOverflow = new StackOverflow();

        try {

            stackOverflow.test();

        }catch (Throwable e){

            e.printStackTrace();
            System.out.println(stackOverflow.depth);
        }

    }
}
