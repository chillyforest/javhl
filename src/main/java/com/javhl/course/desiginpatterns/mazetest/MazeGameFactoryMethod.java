package com.javhl.course.desiginpatterns.mazetest;

/**
 * 以工厂方法的方式创建迷宫游戏，将类的创建延迟到子类
 */
public class MazeGameFactoryMethod {

    public Maze createMaze(){

        Maze maze = makeMaze();

        Room r1 = makeRoom(1);
        Room r2 = makeRoom(2);

        Door theDoor = makeDoor(r1,r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setNorth(makeWall());
        r1.setEast(theDoor);
        r1.setSouth(makeWall());
        r1.setWest(makeWall());

        r2.setNorth(makeWall());
        r2.setEast(makeWall());
        r2.setSouth(makeWall());
        r2.setWest(theDoor);

        return maze;
    }

    /**
     * 提供默任实现，由子类提供具体实现
     * @return
     */
    protected Maze makeMaze(){
        
        return new Maze();
    }
    
    protected Room makeRoom(int roomNo){
        
        return new Room(roomNo);
    }
    
    protected Wall makeWall(){
        
        return  new Wall();
    }
    
    protected  Door makeDoor(Room from,Room to){
        
        return new Door(from,to);
    }
}
