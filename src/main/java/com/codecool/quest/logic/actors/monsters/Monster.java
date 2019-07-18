package com.codecool.quest.logic.actors.monsters;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public abstract class Monster extends Actor {

    public Monster(Cell cell) {
        super(cell);
    }

    abstract public void move();

//    public void notifyRefreshToMonster();

}
