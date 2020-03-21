package com.javhl.course.fstransfer.netty.client;
;
import com.javhl.course.fstransfer.FsManager;
import com.javhl.course.fstransfer.TaskWorker;
import com.javhl.course.fstransfer.po.FsResponse;
import io.netty.channel.ChannelHandlerContext;

public class FsRespWorker extends TaskWorker<FsResponse> {

    public FsRespWorker(ChannelHandlerContext context, FsResponse task) {

        super(context, task);
    }

    @Override
    protected void doTask(FsResponse task) throws Exception {

        FsManager.getSingleton().recordFsResponse(task);
    }
}
