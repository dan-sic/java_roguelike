package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Item implements Drawable {

    public int attackModifier = 0;
    public int durability = 100;
    private Cell cell;

    public Item(Cell cell) { //Constructor, it makes items based on arguments
        this.cell = cell;
        this.cell.setItem(this);
    }

    public void vanishItem(){
        this.cell.setItem(null);
        this.cell = null;
    }
}


