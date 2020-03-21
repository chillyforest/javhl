package com.javhl.course.fstransfer;

public enum  EntityEnum{

    RESP(1),FILE(2);

    private int type;

    EntityEnum(int type){

        this.type = type;
    }

    public int getType(){

        return this.type;
    }
}
