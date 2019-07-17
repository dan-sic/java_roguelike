package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.interfaces.Drawable;
import com.codecool.quest.logic.interfaces.Movable;

public abstract class Actor implements Drawable, Movable {
    protected Cell cell;
    private int health = 10;
    private int attack = 3;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        boolean isMoveValid = isMoveValid(nextCell);

        if (isMoveValid) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

    }

    protected boolean isMoveValid(Cell nextCell) {
        boolean isNextCellWall = nextCell.getType().equals(CellType.WALL);
        boolean isNextCellActor = nextCell.getActor() != null;

        return !isNextCellWall && !isNextCellActor;
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
