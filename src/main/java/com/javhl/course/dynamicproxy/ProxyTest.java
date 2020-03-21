package com.javhl.course.dynamicproxy;

import com.javhl.course.aop.anno.ParamPrintAnno;
import org.springframework.stereotype.Component;

/**
 * @Description 目标类
 */
@Component
public class ProxyTest implements ProxyTestInterface{

    @ParamPrintAnno
    @Override
    public Integer add(Integer a, Integer b) {

        return a+b;
    }

}
