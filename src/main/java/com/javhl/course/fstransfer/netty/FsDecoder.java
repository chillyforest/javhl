package com.javhl.course.fstransfer.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;

import java.util.List;

public class FsDecoder extends ByteToMessageDecoder {

    private AttributeKey<Integer> getFsLenAttr(){

        String name = "fs_len";

        if(AttributeKey.exists(name)){

            return AttributeKey.valueOf(name);
        }

        return AttributeKey.newInstance(name);

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        //length
        if(byteBuf.readableBytes()<4) return;

        Integer fsLen = channelHandlerContext.channel().attr(getFsLenAttr()).get();

        if(null == fsLen){

            fsLen = (int)byteBuf.readUnsignedInt();
            channelHandlerContext.channel().attr(getFsLenAttr()).set(fsLen);
        }

        //可变长度+ type + id
        if(byteBuf.readableBytes() < fsLen+4+32) return;

        //解析
        Integer fsType = (int)byteBuf.readUnsignedInt();

        byte[] idBytes = new byte[32];

        byteBuf.readBytes(idBytes);

        String id = new String(idBytes);

        FsDecoderFactory.getSingleton().getDecoder(fsType).doDecode(id,fsLen,byteBuf,list);
    }
}
