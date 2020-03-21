package com.javhl.course.beans.impl;

import com.javhl.course.beans.service.CalcService;
import org.springframework.stereotype.Component;

@Component
public class MultiplyServiceImpl implements CalcService {
    @Override
    public Integer calc(Integer a, Integer b) {

        return a * b;
    }
}
