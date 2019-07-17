package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.interfaces.Drawable;
import com.codecool.quest.logic.interfaces.Movable;

public abstract class Actor implements Drawable, Movable {
    private Cell cell;
    private int health = 10;
    private int attack = 3;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        boolean isNextCellWall = nextCell.getType().equals(CellType.WALL);
        boolean isNextCellActor = nextCell.getActor() != null;

        if (!isNextCellWall && !isNextCellActor) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
