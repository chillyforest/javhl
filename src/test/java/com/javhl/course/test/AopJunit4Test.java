package com.javhl.course.test;

import com.javhl.course.beans.service.CalcService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AopJunit4Test extends BaseJunit4Test {

    @Autowired
    @Qualifier("addServiceImpl")
    private CalcService calcService;

    @Test
    public void test(){

        Integer ret = calcService.calc(2,10);

        Assert.assertEquals(12,(long)ret);
    }
}
