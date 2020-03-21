package com.javhl.course.fstransfer.netty.server;

import com.javhl.course.PrompLogger;
import com.javhl.course.fstransfer.EntityEnum;
import com.javhl.course.fstransfer.FsManager;
import com.javhl.course.fstransfer.ServerUtil;
import com.javhl.course.fstransfer.TaskWorker;
import com.javhl.course.fstransfer.po.FsEntity;
import com.javhl.course.fstransfer.po.FsResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

public class FsRecordWorker extends TaskWorker<FsEntity> {

    private static PrompLogger logger = PrompLogger.getLogger(FsRecordWorker.class);

    public FsRecordWorker(ChannelHandlerContext context, FsEntity task) {
        super(context, task);
    }

    @Override
    protected void doTask(FsEntity task) throws Exception {

        logger.info("接收到文件，ID=[{}]",task.getId());

        FsEntity fsEntity = task;

        FsResponse fsResponse = new FsResponse();

        fsResponse.setId(task.getId());

        fsResponse.setType(EntityEnum.RESP.getType());

        String host = ServerUtil.getServerIp();

        fsResponse.setLength(host.length()+1);

        fsResponse.setHost(host);

        try {

            FsManager.getSingleton().writeToDisk(fsEntity);

        }catch (IOException e){

            logger.error("写文件异常",e);
        }

    }
}
