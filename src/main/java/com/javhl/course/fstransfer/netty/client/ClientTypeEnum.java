package com.javhl.course.fstransfer.netty.client;

public enum  ClientTypeEnum {

    FILE_SENDER(1),CLASS_LOADER(2);

    private int type;

    ClientTypeEnum(int type){

        this.type = type;
    }

    public int getType(){

        return  type;
    }
}
