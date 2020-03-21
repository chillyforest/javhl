package com.javhl.course.fstransfer.netty;

import io.netty.buffer.ByteBuf;

import java.util.List;

public interface MsgDecoder {

    void doDecode(String id, int len, ByteBuf byteBuf, List<Object> out);
}
