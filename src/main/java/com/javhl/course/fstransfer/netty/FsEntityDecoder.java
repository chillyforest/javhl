package com.javhl.course.fstransfer.netty;

import com.javhl.course.fstransfer.po.FsEntity;
import io.netty.buffer.ByteBuf;

import java.util.List;

public class FsEntityDecoder implements MsgDecoder {

    @Override
    public void doDecode(String id, int fsLen, ByteBuf byteBuf, List<Object> out) {

        byte[] bytes = new byte[fsLen];

        byteBuf.readBytes(bytes);

        out.add(new FsEntity(id,fsLen,bytes));
    }
}
