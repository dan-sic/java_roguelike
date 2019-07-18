package com.codecool.quest.logic.actors.monsters;

import com.codecool.quest.logic.Cell;

public class Golem extends Monster {

    public Golem(Cell cell) {
        super(cell);
        this.health = 15;
        this.attackPower = 3;
        this.isEnemy = true;
        setText(new String[]{"Urgh!","Please don't","I have a wife and family!"});
    }

    private int upCounter = 3;

    public void move() {
        Cell nextCell;

        if(upCounter > 0) {
            nextCell = cell.getNeighbor(0, -1);
            upCounter--;
        }else{
            nextCell = cell.getNeighbor(-1,0);
        }
        if (isMoveValid(nextCell)) {
            changeCell(nextCell);
        }

    }

    @Override
    public String getTileName() {
        return "golem";
    }

    public String getName(){
        return "";
    }

    @Override
    public String toString() {
        return "GOLEM";
    }
}
