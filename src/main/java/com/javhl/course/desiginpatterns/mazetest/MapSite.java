package com.javhl.course.desiginpatterns.mazetest;

import com.javhl.course.PrompLogger;

/**
 * 迷宫构件的抽象接口
 */
public abstract class MapSite {

    protected PrompLogger logger = PrompLogger.getLogger(this.getClass());

    /**
     * 进入操作
     */
    abstract void enter();
}
