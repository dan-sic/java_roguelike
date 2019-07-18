package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.Sword;

import java.util.concurrent.ThreadLocalRandom;

public class Chest extends Interactable {

    private boolean opened;
    private boolean searched;

    public Chest(Cell cell) {
        super(cell);
        opened = false;
        searched = false;
    }

    @Override
    public void Use() {
        opened = true;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public boolean needsKey() {
        return false;
    }

    @Override
    public String getTileName() {
        if(!opened) {
            return "chest_closed";
        } else {

            return "chest_opened";
        }

    }

    @Override
    public Item searchForItems(){
        Item found = null;
        if(!searched) {
            switch (ThreadLocalRandom.current().nextInt(0, 3)) {
                case 0:
                    found = new Sword(null);
                    break;
                case 1:
                    found = new Key(null);
                    break;
                case 2:
                    found = null;
                    break;
            }
            searched = true;
        }
        return found;
    }
}
