package com.javhl.course.desiginpatterns.creatorpatterns;

import com.javhl.course.desiginpatterns.mazetest.Door;
import com.javhl.course.desiginpatterns.mazetest.Maze;
import com.javhl.course.desiginpatterns.mazetest.Room;
import com.javhl.course.desiginpatterns.mazetest.Wall;

public class StandardMazeBuilder implements MazeBuilder {

    private Maze maze;

    @Override
    public void buildMaze() {

        maze = new Maze();
    }

    @Override
    public void buildRoom(int roomNo) {

        if(maze.roomNo(roomNo) == null){

            Room room = new Room(roomNo);
            maze.addRoom(room);

            room.setWest(new Wall());
            room.setSouth(new Wall());
            room.setEast(new Wall());
            room.setNorth(new Wall());
        }
    }

    @Override
    public void buildDoor(int roomFromNo, int roomToNo) {

        Room r1 = maze.roomNo(roomFromNo);
        Room r2 = maze.roomNo(roomToNo);

        Door door = new Door(r1,r2);
        r1.setEast(door);
        r2.setWest(door);
    }

    @Override
    public Maze getMaze() {
        return maze;
    }
}
