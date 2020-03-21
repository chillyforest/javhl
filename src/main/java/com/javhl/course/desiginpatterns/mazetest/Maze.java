package com.javhl.course.desiginpatterns.mazetest;

import java.util.HashMap;
import java.util.Map;

/**
 * 迷宫类
 */
public class Maze {

    public Maze(){

        roomMap = new HashMap<>();
    }

    public void addRoom(Room room){

        roomMap.put(room.getRoomNo(),room);
    }

    public Room roomNo(Integer roomNumber){

        return roomMap.get(roomNumber);
    }

    public void enter(){

        roomMap.forEach((roomNo,room)->{

            room.enter();
        });
    }

    private Map<Integer,Room> roomMap;
}
