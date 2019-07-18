package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;

public class Chest extends Interactable {

    public Chest(Cell cell) {
        super(cell);
    }

    boolean opened = false;

    @Override
    public void Use() {
        opened = true;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public boolean needsKey() {
        return false;
    }

    @Override
    public String getTileName() {
        if(!opened) {
            return "chest_closed";
        } else {

            return "chest_opened";
        }

    }
}
