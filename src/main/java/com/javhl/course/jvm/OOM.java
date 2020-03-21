package com.javhl.course.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class OOM {

    public static void main(String[] args){

        int _1M = 1024*1024;

        //强应用
        List<Byte[]> bytes = new ArrayList<>();

        //软引用
        List<SoftReference<Byte[]>> softReferenceList = new ArrayList<>();

        //弱引用
        List<WeakReference<Byte[]>> weakReferencesList = new ArrayList<>();

        //读取启动时传入的参数,判断是否是强引用
        boolean isStrongRef = Boolean.parseBoolean(System.getProperty("strongRef"));

        for(int i=0;i<10;i++){

            Byte[] temp = new Byte[_1M];
            //注释掉后可以正常回收
            if(isStrongRef) {

                bytes.add(temp);

            }else {

//                softReferenceList.add(new SoftReference<>(temp));
                weakReferencesList.add(new WeakReference<Byte[]>(temp));
            }

            System.out.println("申请第："+(i+1)+"个对象");
        }

        //获取软引用中的对象
        for(int i=0; i<softReferenceList.size();i++){

            SoftReference<Byte[]> softReference = softReferenceList.get(i);

            if(softReference.get()!=null){

                System.out.println("第 "+(i+1) + " 个对象不为空");
            }
        }

        System.out.println("success!");
    }
}
