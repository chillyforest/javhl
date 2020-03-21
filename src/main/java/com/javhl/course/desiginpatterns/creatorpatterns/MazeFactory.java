package com.javhl.course.desiginpatterns.creatorpatterns;

import com.javhl.course.desiginpatterns.mazetest.Door;
import com.javhl.course.desiginpatterns.mazetest.Maze;
import com.javhl.course.desiginpatterns.mazetest.Room;
import com.javhl.course.desiginpatterns.mazetest.Wall;

/**
 * 迷宫工厂
 */
public class MazeFactory {

    public Maze makeMaze(){

        return new Maze();
    }

    public Wall makeWall(){

        return new Wall();
    }

    public Room makeRoom(int roomNo){

        return new Room(roomNo);
    }

    public Door makeDoor(Room from,Room to){

        return new Door(from,to);
    }
}
