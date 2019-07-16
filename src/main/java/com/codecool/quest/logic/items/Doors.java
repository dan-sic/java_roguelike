package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
//import com.codecool.quest.logic.items;

public class Doors extends Item {
    public Doors(Cell cell) {
        super(cell);
    }

    String opened = "doors";

    public void open() {
        opened = "doors_opened";
    }

    @Override
    public String getTileName() {
        if(opened.equals("doors")) {

            return "doors";
        } else {

            return "doors_opened";
        }

    }
}
