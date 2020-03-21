package com.javhl.course.desiginpatterns.mazetest;

import lombok.Data;

/**
 * 迷宫的房间
 */
@Data
public class Room extends MapSite {

    /**
     * 房间号
     */
    private int roomNo;

    /**
     * 房间的4个方向
     */
    private MapSite north;
    private MapSite east;
    private MapSite south;
    private MapSite west;

    public Room(int roomNo){

        this.roomNo = roomNo;
    }

    @Override
    public void enter() {

        logger.info("进入房间:[{}],北面：",roomNo);
        north.enter();
        logger.info("进入房间:[{}],东面：",roomNo);
        east.enter();
        logger.info("进入房间:[{}],南面：",roomNo);
        south.enter();
        logger.info("进入房间:[{}],西面：",roomNo);
        west.enter();
    }


}
