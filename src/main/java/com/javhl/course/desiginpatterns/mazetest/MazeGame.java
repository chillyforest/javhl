package com.javhl.course.desiginpatterns.mazetest;

import com.javhl.course.desiginpatterns.creatorpatterns.MazeBuilder;
import com.javhl.course.desiginpatterns.creatorpatterns.MazeFactory;
import com.javhl.course.desiginpatterns.creatorpatterns.StandardMazeBuilder;

public class MazeGame {

    /**
     * 创建迷宫，硬编码形式，不同的构件如何创建迷宫？创建一个迷宫还需要知道门、房间？
     * @return
     */
    public static Maze createMaze(){

        Maze maze = new Maze();

        Room r1 = new Room(1);
        Room r2 = new Room(2);

        Door theDoor = new Door(r1,r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setNorth(new Wall());
        r1.setEast(theDoor);
        r1.setSouth(new Wall());
        r1.setWest(new Wall());

        r2.setNorth(new Wall());
        r2.setEast(new Wall());
        r2.setSouth(new Wall());
        r2.setWest(theDoor);

        return maze;

    }

    /**
     * 创建迷宫，用抽象工厂模式，可以灵活替换构件
     * @return
     */
    public static Maze createMaze(MazeFactory factory){

        Maze maze = factory.makeMaze();

        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);

        Door theDoor = factory.makeDoor(r1,r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setNorth(factory.makeWall());
        r1.setEast(theDoor);
        r1.setSouth(factory.makeWall());
        r1.setWest(factory.makeWall());

        r2.setNorth(factory.makeWall());
        r2.setEast(factory.makeWall());
        r2.setSouth(factory.makeWall());
        r2.setWest(theDoor);

        return maze;

    }

    /**
     * 创建迷宫，用建造者模式，可以简化构建过程
     * @return
     */
    public static Maze createMaze(MazeBuilder builder){

        builder.buildMaze();
        builder.buildRoom(1);
        builder.buildRoom(2);
        builder.buildDoor(1,2);

        return builder.getMaze();

    }

    public static void main(String[] args){

        Maze maze;

        //硬编码模式
        maze = createMaze();

        //抽象工厂模式
        maze = createMaze(new MazeFactory());

        //建造者模式
        maze = createMaze(new StandardMazeBuilder());

        maze.enter();

    }
}
