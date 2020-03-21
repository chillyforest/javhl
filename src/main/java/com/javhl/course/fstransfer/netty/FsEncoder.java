package com.javhl.course.fstransfer.netty;

import com.javhl.course.fstransfer.po.BaseEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FsEncoder extends MessageToByteEncoder<BaseEntity> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BaseEntity baseEntity, ByteBuf byteBuf) throws Exception {

        byteBuf.writeInt(baseEntity.getLength());
        byteBuf.writeInt(baseEntity.getType());
        byteBuf.writeBytes(baseEntity.getId().getBytes());
        byteBuf.writeBytes(baseEntity.getData());

        //释放bytebuf
        baseEntity.getData().release();
    }
}
