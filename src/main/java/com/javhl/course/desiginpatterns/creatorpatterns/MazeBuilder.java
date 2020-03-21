package com.javhl.course.desiginpatterns.creatorpatterns;

import com.javhl.course.desiginpatterns.mazetest.Maze;

public interface MazeBuilder {

    void buildMaze();

    void buildRoom(int roomNo);

    void buildDoor(int roomFromNo,int roomToNo);

    Maze getMaze();
}
