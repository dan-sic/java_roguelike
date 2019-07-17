package com.codecool.quest.logic;

import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.Sword;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {

    private ArrayList<Item> inventory = new ArrayList<>();


    public Inventory(){
    }

    public String toString(){
        StringBuilder itemsSB = new StringBuilder();
        if(!(inventory.size() == 0)){
            for(Item item: inventory){
                itemsSB.append(" » ");
                itemsSB.append(item.getTileName().toUpperCase());
                itemsSB.append("\n");
            }
        }else{
            itemsSB.append(" » EMPTY");
        }
        return itemsSB.toString();
    }

    public void addItem(Item itemToAdd){
        inventory.add(itemToAdd);
    }

    public void removeItem(Item itemToRemove){
        inventory.remove(itemToRemove);
    }
}
