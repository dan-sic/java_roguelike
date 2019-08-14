package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.interactable.Interactable;
import com.codecool.quest.logic.items.Item;


public class Player extends Actor {

    private Inventory playerInventory;
    private String name;

    private Item currentWeapon = null;
    private Item currentArmor = null;

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
        currentWeapon = newWeapon;
    }

    public void changeEquippedArmor(Item newArmor) { currentArmor = newArmor; }

    public int getEquippedWeaponAttack(){
        return currentWeapon.getAttackModifier();
    }

    @Override
    public int getDefense(){
        if (currentArmor != null)
            return currentArmor.getDefenseModifier();
        else
            return 0;
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
            if(item.getTileName().equals("armor"))
                changeEquippedArmor(item);

            return true;
        }
        return false;
    }

    public String attack(){
        Actor actor = getNextCell().getActor();
        if(actor!= null) {
            if(actor.isEnemy) {
                if (currentWeapon != null) {
                    //actor.printHealth("before");
                    actor.receiveAttack((getAttackPower() + getEquippedWeaponAttack()), actor.getDefense(), this);

                    currentWeapon.setDurability(-30);

                    if (currentWeapon.getDurability() <= 0) {
                        currentWeapon = null;
                        this.playerInventory.removeItem("sword");
                    }
                } else
                    actor.receiveAttack(getAttackPower(), actor.getDefense(), this);
            }
        }
        if(!this.isDead()) {
            return getAttackMessage();
        }else{
            return "";
        }
    }

    public String talk(){
        Actor actor = getNextCell().getActor();
        if(actor != null) {
            String message = actor.getNextText();
            if(actor.isEnemy) {
                return String.format("%s doesn't want to talk with you.", actor);
            }
            return message;
        }
        return null;
    }

    private String getAttackMessage(){
        Actor actor = getNextCell().getActor();
        if(actor != null) {
            String message = actor.getNextText();
            if(actor.isEnemy) {
                return message;
            }
            return "Don't do that!";
        }
        return null;
    }

    public String interactWithObject(Interactable interactableItem) {
        if(interactableItem.needsKey()) {
            if (playerInventory.checkForItem("key")) {
                interactableItem.Use();
                playerInventory.removeItem("key");
                return "";
            }
        }else{
            interactableItem.Use();
            Item found = interactableItem.searchForItems();
            if(found != null) {
                playerInventory.addItem(found);
                return "Found a " + found;
            }else{
                return "Found nothing.";
            }
        }

        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void updatePlayerNewPosition(Player nextLevelPlayer){
        this.changeCell(nextLevelPlayer.getCell());
    }

    public boolean isOnStairs(){
        return this.getCell().getType()== CellType.STAIRS;
    }

}
