package com.javhl.course.fstransfer.netty;

import com.javhl.course.fstransfer.EntityEnum;

public class FsDecoderFactory {

    private static volatile FsDecoderFactory singleton;

    private FsDecoderFactory(){}

    public static FsDecoderFactory getSingleton(){

        if(null == singleton){

            synchronized (FsDecoderFactory.class){

                if(null == singleton){

                    singleton = new FsDecoderFactory();
                }
            }
        }

        return singleton;
    }

    public MsgDecoder getDecoder(int type){

        if(type == EntityEnum.FILE.getType() ){

            return new FsEntityDecoder();

        }else if(type == EntityEnum.RESP.getType()){

            return new FsEntityRespDecoder();
        }

       return null;

    }
}
