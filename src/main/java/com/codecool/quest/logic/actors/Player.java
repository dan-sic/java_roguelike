package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.actors.Actor;
import com.sun.org.apache.xerces.internal.xs.StringList;


public class Player extends Actor {
    private Inventory playerInventory;

    public Player(Cell cell) {
        super(cell);
        playerInventory = new Inventory();
    }

    public String getTileName() {
        return "player";
    }

    public Inventory getPlayerInventory(){
        return playerInventory;
    }

}
