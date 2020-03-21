package com.javhl.course.fstransfer.netty.client;

import com.javhl.course.PrompLogger;
import com.javhl.course.fstransfer.FsManager;
import com.javhl.course.fstransfer.TaskWorker;
import com.javhl.course.fstransfer.po.FsEntity;
import io.netty.channel.ChannelHandlerContext;

public class FsFileSenderTaskWorker extends TaskWorker<FsEntity> {

    private static final PrompLogger logger = PrompLogger.getLogger(FsFileSenderTaskWorker.class);

    public FsFileSenderTaskWorker(ChannelHandlerContext context, FsEntity task) {

        super(context, task);
    }

    @Override
    protected void doTask(FsEntity task) throws Exception {

        FsEntity fsEntity = FsManager.getSingleton().readFromDisk(task);

        getContext().writeAndFlush(fsEntity);

        logger.info("文件发送成功,ID=[{}]",fsEntity.getId());
    }
}
