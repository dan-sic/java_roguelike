package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.items.Item;

public class Player extends Actor {
    private Inventory playerInventory;

    public Player(Cell cell) {
        super(cell);
        playerInventory = new Inventory();
    }

    public String getTileName() {
        return "player";
    }

    public void pickItem(){
        Cell cell = this.getCell();
        if(cell.getItem() != null){
            Item item = cell.getItem();
            item.vanishItem();
            playerInventory.addItem(item);
        }
    }

    public Inventory getPlayerInventory(){
        return playerInventory;
    }

    //public void attack

}
