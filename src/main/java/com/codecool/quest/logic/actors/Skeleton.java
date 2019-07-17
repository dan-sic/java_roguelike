package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Skeleton extends Monster {

    private int health = 6;
    private int attack = 1;

    public Skeleton(Cell cell) {
        super(cell);
    }

    public void move() {

        boolean isValidCoords = false;
        Cell nextCell = null;

        while (!isValidCoords) {
            int randomXPos = ThreadLocalRandom.current().nextInt(-1, 1 + 1);
            int randomYPos = ThreadLocalRandom.current().nextInt(-1, 1 + 1);

            nextCell = cell.getNeighbor(randomXPos, randomYPos);

            boolean isMoveValid = isMoveValid(nextCell);

            if (isMoveValid) {
                isValidCoords = true;
            }
        }

        changeCell(nextCell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
