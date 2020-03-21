package com.javhl.course.fstransfer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerManager {

    private static volatile WorkerManager singleton;

    private WorkerManager(){

    }

    public static WorkerManager getSingleton(){

        if(null == singleton){

            synchronized (WorkerManager.class){

                if(null == singleton){

                    singleton = new WorkerManager();
                }
            }
        }

        return singleton;
    }

    public void init(int poolSize){

        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void close(){

        pool.shutdown();
    }

    public void run(TaskWorker worker){

        if(null == pool) throw new IllegalStateException("pool not init...");

        pool.submit(worker);
    }

    private ExecutorService pool;

}
