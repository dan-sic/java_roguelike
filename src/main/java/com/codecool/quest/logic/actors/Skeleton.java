package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        this.isEnemy = true;
        this.health = 10;
        this.attackPower = 5;
        setText(new String[]{"Urgh!","Please don't","I have a wife and family!"});
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public String getName(){
        return "";
    }
}
