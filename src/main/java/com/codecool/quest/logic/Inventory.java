package com.codecool.quest.logic;

import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.Sword;
import com.codecool.quest.Main;

import java.util.ArrayList;
import java.util.Arrays;

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
}
