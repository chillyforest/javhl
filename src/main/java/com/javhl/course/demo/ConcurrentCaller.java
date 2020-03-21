package com.javhl.course.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentCaller {


    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Future> futures = new ArrayList<>();

        long time = System.currentTimeMillis();

        for(int i=0;i<3;i++){

            Task task = new Task("task"+(i+1),1000*(i+1));

            futures.add(executorService.submit(task));
        }

        List<Object> results = new ArrayList<>();


        for(Future future : futures){

            TaskResult<Integer> result = new TaskResult<>();

            try {

                Integer ret = (Integer) future.get();

                result.setData(ret);

                results.add(result);

                System.out.println("执行结果："+ret);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                result.setException(true);
                result.setExecutionExcepiton(e.getCause());
            }

        }

        long timeUsed = System.currentTimeMillis() - time;

        System.out.println("总耗时："+timeUsed +" ms");

    }
}
