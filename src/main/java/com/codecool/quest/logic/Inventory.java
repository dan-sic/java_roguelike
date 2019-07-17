package com.codecool.quest.logic;

import com.codecool.quest.logic.items.Item;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> inventory = new ArrayList<>();


    public Inventory(){
    }

    public String toString(){
        StringBuilder items = new StringBuilder();
        for(Item item: inventory){
            items.append(item.getTileName());
            items.append("\n");
        }
        return items.toString();
    }

    public void addItem(Item itemToAdd){
        inventory.add(itemToAdd);
    }

    public void removeItem(Item itemToRemove){
        inventory.remove(itemToRemove);
    }

    public void removeItem(String itemName){
        for (int i=0; i<inventory.size();i++){
            if( itemName.equals(inventory.get(i).getTileName())){
                inventory.remove(i);
                break;
            }
        }
    }

    public Boolean checkForItem(String itemName){
        Boolean isItem = false;
        for (int i=0; i<inventory.size();i++) {
            if (itemName.equals(inventory.get(i).getTileName())) {
                isItem = true;
            }
        }
        return isItem;
    }

}
