package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Ghost extends Monster {

    private int health = 8;
    private int attack = 2;

    public Ghost(Cell cell) {
        super(cell);
    }

    public void move() {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//
//        boolean isNextCellWall = nextCell.getType().equals(CellType.WALL);
//        boolean isNextCellActor = nextCell.getActor() != null;
//
//        if (!isNextCellWall && !isNextCellActor) {
//            cell.setActor(null);
//            nextCell.setActor(this);
//            cell = nextCell;
//        }
        System.out.println("Ghost move");
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
