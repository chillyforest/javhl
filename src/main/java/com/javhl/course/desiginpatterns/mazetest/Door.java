package com.javhl.course.desiginpatterns.mazetest;

import lombok.Data;

@Data
public class Door extends MapSite {

    private Room from;
    private Room to;
    private boolean isOpen;

    public Door(Room from,Room to){

        this.from = from;
        this.to = to;
    }

    @Override
    void enter() {

        logger.info("door is [{}],from Room=[{}] to Room=[{}]",isOpen?"open":"lock",from.getRoomNo(),to.getRoomNo());
    }
}
