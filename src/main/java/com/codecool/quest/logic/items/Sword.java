package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Sword extends Item {

    public Sword(Cell cell) {
        super(cell);
        this.attackModifier = 5;
        this.durability = 100;
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    @Override
    public String toString() {
        return "SWORD";
    }

    // sword check durability method and delete sword method

}
