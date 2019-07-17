package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Golem extends Monster {

    private int health = 15;
    private int attack = 3;

    public Golem(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "golem";
    }
}
