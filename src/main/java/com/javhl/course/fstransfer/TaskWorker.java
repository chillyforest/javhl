package com.javhl.course.fstransfer;

import io.netty.channel.ChannelHandlerContext;

public abstract class TaskWorker<T> implements Runnable {

    public TaskWorker(ChannelHandlerContext context,T task){

        this.channelHandlerContext = context;
        this.task = task;
    }

    @Override
    public void run() {

        try {
            doTask(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    abstract protected void doTask(T task) throws Exception;

    public ChannelHandlerContext getContext(){

        return channelHandlerContext;
    }

    private ChannelHandlerContext channelHandlerContext;
    private T task;

}
