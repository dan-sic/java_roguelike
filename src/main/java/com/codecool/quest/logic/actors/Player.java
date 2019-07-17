package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.items.Item;
import com.sun.org.apache.xerces.internal.xs.StringList;


public class Player extends Actor {

    private Inventory playerInventory;

    private int health = 15;

    public Player(Cell cell) {

        super(cell);
        playerInventory = new Inventory();
    }

    public String getTileName() {

        return "player";
    }

    public boolean pickItem(){
        Cell cell = this.getCell();
        if(cell.getItem() != null){
            Item item = cell.getItem();
            item.vanishItem();
            playerInventory.addItem(item);
            return true;
        }
        return false;
    }

    public Inventory getPlayerInventory(){
        return playerInventory;
    }

    public Cell getNextCell(){
        switch (getDirection()){
            case "up":
                return getCell().getNeighbor(0,1);
            case "down":
                return getCell().getNeighbor(0,-1);
            case "left":
                return getCell().getNeighbor(-1,0);
            case "right":
                return getCell().getNeighbor(1,0);
        }
        return getCell();
    }

    public void attack(){
        if(getNextCell().getActor() != null) {
            getNextCell().getActor().receiveAttack(getAttackPower());
        }
    }

    public void printDirection(){
        System.out.println(this.getDirection());
    }
}
