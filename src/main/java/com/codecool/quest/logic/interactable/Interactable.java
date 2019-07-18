package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.interfaces.Drawable;

public abstract class Interactable implements Drawable {

    private Cell cell;

    public Interactable(Cell cell) { //Constructor, it makes items based on arguments
        this.cell = cell;
        this.cell.setInteractable(this);
    }

    public abstract void Use();
    public abstract boolean isPassable();
    public abstract boolean needsKey();


//    public void openDoor(){
//        this.cell.setItem(doors_opened);
////        this.cell = null;
//    }
}