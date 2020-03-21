package com.javhl.course.desiginpatterns.mazetest;

public class Wall extends MapSite{

    public Wall(){

    }

    @Override
    void enter() {

        logger.info("this is a wall");
    }
}
