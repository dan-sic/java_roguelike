package com.codecool.quest.logic.actors;

public enum NPCType {
    WIZARD("wizard", new String[]{"Hello Adventurer!", "Please don't do that!", "Well I never!"}),
    GUARD("guard", new String[]{"For King And The Country!!", "I am watching you!", "I used to be an adventurer like you."});

    private final String tileName;
    private final String[] npcText;

    NPCType(String tileName, String[] npcText) {
        this.tileName = tileName;
        this.npcText = npcText;
    }

    public String getTileName() {
        return tileName;
    }
    public String[] getNpcText() {
        return npcText;
    }
}
