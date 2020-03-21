package com.javhl.course;

public class VolatileTest {

    static class Task implements Runnable{

        private boolean flag = false;

        @Override
        public void run() {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flag = true;

//            System.out.println("flag = "+flag);

        }

        public boolean isFlag() {
            return flag;
        }

        public synchronized void lockTest(){


        }
    }

    public static void main(String[] args) throws Exception {

        Task task = new Task();

        Thread t = new Thread(task);

        t.start();

        int count = 0;

        while (true){

            count++;

//            System.out.println("111");

            task.lockTest();

//            Thread.sleep(1);

            if(task.isFlag()){

                System.out.println("count = "+count);

                break;
            }
        }
    }
}