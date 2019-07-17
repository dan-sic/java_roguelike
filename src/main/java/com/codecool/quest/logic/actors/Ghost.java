package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.MapLoader;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost extends Monster {

    private int health = 8;
    private int attack = 2;
    private int moveCounter = 0;

    public Ghost(Cell cell) {
        super(cell);
    }

    public void move() {

        incrementMoveCounter();

        if (moveCounter < 5) return;
//
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
}
