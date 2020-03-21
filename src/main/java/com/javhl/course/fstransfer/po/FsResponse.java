package com.javhl.course.fstransfer.po;

import com.javhl.course.fstransfer.EntityEnum;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.Data;

@Data
public class FsResponse extends BaseEntity {

    /**
     * 状态1成功，0失败
     */
    private byte status;

    /**
     * 主机IP
     */
    private String host;

    public FsResponse(){

    }

    public FsResponse(String id,byte status,int length,String host){

        super(EntityEnum.RESP.getType(),id,length);

        this.status = status;
        this.host = host;
    }

    @Override
    public ByteBuf getData() {

        ByteBuf bf = PooledByteBufAllocator.DEFAULT.buffer();

        bf.writeByte(status);
        bf.writeBytes(host.getBytes());

        return bf;
    }
}
