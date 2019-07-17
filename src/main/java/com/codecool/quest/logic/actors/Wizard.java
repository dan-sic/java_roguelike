package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Wizard extends Actor {

    public Wizard(Cell cell) {
        super(cell);
        this.isEnemy = false;
        this.health = 20;
        this.setText(new String[]{"Hello Adventurer!", "Please don't do that!", "Well I never!"});
    }

    @Override
    public String getName() {
        return "Gandalf";
    }

    @Override
    public String getTileName() {
        return "wizard";
    }


}
