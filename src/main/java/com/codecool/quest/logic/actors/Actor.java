package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private String[] cheatNames = {"Michał", "Piotrek", "Janek", "Olek", "Daniel"};

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        List<String> cheatNamesList = Arrays.asList(cheatNames);
        String actorName = cell.getActor().getName();

        boolean isNextCellWall = nextCell.getType().equals(CellType.WALL);
        boolean isNextCellActor = nextCell.getActor() != null;

        if (!isNextCellWall && !isNextCellActor) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        //checks if player name equals any name on cheatList
        }else if(cheatNamesList.contains(actorName)){
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

    public abstract String getName();
}
