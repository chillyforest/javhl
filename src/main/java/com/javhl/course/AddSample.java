package com.javhl.course;

/**
 * @Description 相加测试
 */
public class AddSample {

    private volatile Integer count = 0;

    public void add() {
        count++;
    }
    public static void main(String[] args) {

        final AddSample addSample = new AddSample();

        for(int i=0;i<100;i++){

            new Thread(() -> {

                for (int j = 0; j < 10; j++) {

                    addSample.add();
                }

            }).start();
        }
        while (Thread.activeCount()>1){//让启动的100个线程都执行结束

            Thread.yield();
        }
        System.out.println(addSample.count);
    }
}
