package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Ghost extends Monster {

    private int health = 8;
    private int attack = 2;

    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
