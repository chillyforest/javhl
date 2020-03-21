package com.javhl.course.demo;

import lombok.Data;

@Data
public class TaskResult<T> {

    private T data;
    private boolean exception = false;
    private Throwable executionExcepiton;
}
