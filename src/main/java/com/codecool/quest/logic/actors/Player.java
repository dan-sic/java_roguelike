package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.items.Item;


public class Player extends Actor {

    private Inventory playerInventory;

    private Item currentlyEquipped = null;

    public Player(Cell cell) {
        super(cell);
        playerInventory = new Inventory();
        this.changeHealth(5);
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

            if(item.getTileName().equals("sword"))
                changeEquippedWeapon(item);

            return true;
        }
        return false;
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

    public void attack(){
        if(getNextCell().getActor() != null) {
            if (currentlyEquipped != null){
                getNextCell().getActor().printHealth("before");
                getNextCell().getActor().receiveAttack((getAttackPower() + getEquippedWeaponAttack()), this);

                currentlyEquipped.durability -= 30;

                if (currentlyEquipped.durability <= 0)
                    currentlyEquipped = null;
            }
            else
                getNextCell().getActor().receiveAttack(getAttackPower(), this);
        }
    }
}
