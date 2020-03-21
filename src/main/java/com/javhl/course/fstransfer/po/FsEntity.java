package com.javhl.course.fstransfer.po;

import com.javhl.course.fstransfer.EntityEnum;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.Data;

@Data
public class FsEntity extends BaseEntity {

    private byte[] fsData;

    public FsEntity(String fsId,int fLen,byte[] fData){

       super(EntityEnum.FILE.getType(),fsId,fLen);
       this.fsData = fData;
    }

    public FsEntity(String fsId){

        super(EntityEnum.FILE.getType(),fsId,0);
    }

    @Override
    public ByteBuf getData() {

        ByteBuf bf = PooledByteBufAllocator.DEFAULT.buffer();
        bf.writeBytes(fsData);

        return bf;
    }
}
