package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.items.Item;


public class Player extends Actor {

    private Inventory playerInventory;
    private String name;

    private Item currentlyEquipped = null;

    public Player(Cell cell) {
        super(cell);
        playerInventory = new Inventory();
        this.isEnemy = false;
        this.health = 15;
        this.attackPower = 5;
    }

    public String getTileName() {

        return "player";
    }

    public void changeEquippedWeapon(Item newWeapon){
        currentlyEquipped = newWeapon;
    }

    public int getEquippedWeaponAttack(){
        return currentlyEquipped.attackModifier;
    }

    public Inventory getPlayerInventory(){
        return playerInventory;
    }

    public Cell getNextCell(){
        switch (getDirection()){
            case "up":
                return getCell().getNeighbor(0,-1);
            case "down":
                return getCell().getNeighbor(0,1);
            case "left":
                return getCell().getNeighbor(-1,0);
            case "right":
                return getCell().getNeighbor(1,0);
        }
        return getCell();
    }

    public boolean pickItem(){
        Cell cell = this.getCell();
        if(cell.getItem() != null){
            Item item = cell.getItem();
            item.vanishItem();
            playerInventory.addItem(item);

            if(item.getTileName().equals("sword"))
                changeEquippedWeapon(item);

            return true;
        }
        return false;
    }

    public String getLastItemPicked(){
        return playerInventory.getLastItem().getTileName();
    }

    private void attack(Actor actor){
        if (currentlyEquipped != null){
            //actor.printHealth("before");
            actor.receiveAttack((getAttackPower() + getEquippedWeaponAttack()),this);

            currentlyEquipped.durability -= 30;

            if (currentlyEquipped.durability <= 0) {
                currentlyEquipped = null;
                this.playerInventory.removeItem("sword");
            }
        }
        else
            actor.receiveAttack(getAttackPower(),this);
    }

    public String interactWithActor(){
        Actor actor = getNextCell().getActor();
        if(actor != null) {
            String message = actor.getNextText();
            if(actor.isEnemy) {
                attack(actor);
            }
            return message;
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
