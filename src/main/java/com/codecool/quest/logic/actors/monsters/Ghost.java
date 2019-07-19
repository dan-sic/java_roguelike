package com.codecool.quest.logic.actors.monsters;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.MapLoader;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost extends Monster {

    private int moveCounter = 0;

    public Ghost(Cell cell) {
        super(cell);
        this.health = 8;
        this.attackPower = 2;
        this.isEnemy = true;
        setText(new String[]{"*Puff*","Please don't","I have a wife and family!"});
    }

    public void move() {

        incrementMoveCounter();

        if (moveCounter < 5) return;

        boolean isValidCoords = false;
        Cell nextCell = null;

        while (!isValidCoords) {
            int randomXPos = ThreadLocalRandom.current().nextInt(1, 25);
            int randomYPos = ThreadLocalRandom.current().nextInt(1, 20);

            try {
                nextCell = MapLoader.getCurrentMap().getCell(randomXPos, randomYPos);

                boolean isCellFloor = nextCell.getType().equals(CellType.FLOOR);
                boolean isMoveValid = isMoveValid(nextCell);

                if (isMoveValid && isCellFloor) {
                    isValidCoords = true;
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

        changeCell(nextCell);
    }

    private void incrementMoveCounter() {
        if (moveCounter < 5) {
            moveCounter++;
        } else  {
            moveCounter = 0;
        }
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    public String getName(){
        return "";
    }

    @Override
    public String toString(){
        return "GHOST";
    }
}
