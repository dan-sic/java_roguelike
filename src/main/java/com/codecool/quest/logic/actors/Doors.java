package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Doors extends Actor {
    public Doors(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "doors";
    }
}
