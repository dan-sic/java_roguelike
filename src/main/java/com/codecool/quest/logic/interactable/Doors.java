package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.items.Item;
//import com.codecool.quest.logic.items;

public class Doors extends Interactable {
    public Doors(Cell cell) {
        super(cell);
    }

    Boolean opened = false;

    public void Use() {
        opened = true;
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
