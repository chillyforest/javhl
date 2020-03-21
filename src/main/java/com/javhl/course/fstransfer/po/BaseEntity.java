package com.javhl.course.fstransfer.po;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseEntity implements Serializable {

    public BaseEntity(){

    }

    public BaseEntity(int type,String id,int length){

        this.type = type;
        this.length = length;
        this.id = id;
    }

    /**
     * 实体类型
     */
    private int type;
    /**
     * 长度，bytes
     */
    private int length;
    /**
     * 实体ID
     */
    private String id;

    public abstract ByteBuf getData();

}
