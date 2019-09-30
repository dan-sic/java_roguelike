package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.items.Item;

public class Doors extends Interactable {
    public Doors(Cell cell) {
        super(cell);
    }

    boolean opened = false;

    @Override
    public void Use() {
        opened = true;
    }

    @Override
    public boolean isPassable() {
        return opened;
    }

    @Override
    public boolean needsKey() {
        return true;
    }

    @Override
    public String getTileName() {
        if(!opened) {
            return "doors";
        } else {

            return "doors_opened";
        }

    }
}
