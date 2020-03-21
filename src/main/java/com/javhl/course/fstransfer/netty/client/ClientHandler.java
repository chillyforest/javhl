package com.javhl.course.fstransfer.netty.client;

import com.javhl.course.fstransfer.EntityEnum;
import com.javhl.course.fstransfer.po.BaseEntity;
import com.javhl.course.fstransfer.po.FsEntity;
import com.javhl.course.fstransfer.po.FsResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ClientTypeEnum clientType;
    private String loaderId;

    public ClientHandler(ClientTypeEnum type,String loaderId){

        this.clientType = type;

        this.loaderId = loaderId;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        BaseEntity baseEntity = (BaseEntity)msg;

        if(baseEntity.getType() == EntityEnum.RESP.getType()) {

            FsResponse response = (FsResponse) msg;
            //WorkerManager.getSingleton().run(new FsRespWorker(ctx,response));
//            new Thread(new FsRespWorker(ctx,response)).start();
        }

        ctx.close();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        if(clientType == ClientTypeEnum.FILE_SENDER){

            new Thread(new FsFileSenderTaskWorker(ctx,new FsEntity(loaderId))).start();

        }else if(clientType == ClientTypeEnum.CLASS_LOADER){


        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
