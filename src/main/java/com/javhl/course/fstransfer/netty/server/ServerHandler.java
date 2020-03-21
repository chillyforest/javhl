package com.javhl.course.fstransfer.netty.server;

import com.javhl.course.PrompLogger;
import com.javhl.course.fstransfer.EntityEnum;
import com.javhl.course.fstransfer.WorkerManager;
import com.javhl.course.fstransfer.po.BaseEntity;
import com.javhl.course.fstransfer.po.FsEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static final PrompLogger logger = PrompLogger.getLogger(ServerHandler.class);

    public ServerHandler(){


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        super.channelActive(ctx);

        logger.info("与客户端建立连接成功，通道ID=[{}]",ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        BaseEntity baseEntity = (BaseEntity)msg;

        if(baseEntity.getType() == EntityEnum.FILE.getType()){

            FsEntity fsEntity = (FsEntity)msg;
            WorkerManager.getSingleton().run(new FsRecordWorker(ctx,fsEntity));
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.state() == IdleState.ALL_IDLE){
                ctx.close();
                return;
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();

    }
}
