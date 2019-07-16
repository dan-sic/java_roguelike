package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Doors extends Actor {
    public Doors(Cell cell) {
        super(cell);
    }

    boolean opened = false;

    public void open() {
        opened = true;
    }

    @Override
    public String getTileName() {
        if(!opened) {
            return "doors";
        } else return "doors_opened";

    }
}
