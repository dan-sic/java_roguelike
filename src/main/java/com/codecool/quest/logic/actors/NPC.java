package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class NPC extends Actor {

    private String npcType;

    public NPC(Cell cell, NPCType npc) {
        super(cell);
        this.isEnemy = false;
        this.health = 20;
        this.setText(npc.getNpcText());
        npcType = npc.getTileName();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getTileName() {
        return npcType;
    }

}

