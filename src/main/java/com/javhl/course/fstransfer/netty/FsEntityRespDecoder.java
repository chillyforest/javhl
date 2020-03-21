package com.javhl.course.fstransfer.netty;

import com.javhl.course.fstransfer.po.FsResponse;
import io.netty.buffer.ByteBuf;

import java.util.List;

public class FsEntityRespDecoder implements MsgDecoder {
    @Override
    public void doDecode(String id,int rspLen,ByteBuf byteBuf,List<Object> out) {

        byte status = byteBuf.readByte();

        byte[] tmpBytes = new byte[rspLen-1];

        byteBuf.readBytes(tmpBytes);

        String host = new String(tmpBytes);

        FsResponse response = new FsResponse(id,status,rspLen,host);

        out.add(response);
    }
}
