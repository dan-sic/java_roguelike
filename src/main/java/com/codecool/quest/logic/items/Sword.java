package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Sword extends Item {
    @Override
    public String getTileName() {
        return "sword";
    }

    public Sword(Cell cell) {
        super(cell);
    }

    public int attackModifier = 10;

    private int durability = 100;

    // sword check durability method and delete sword method

}
