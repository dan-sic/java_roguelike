package com.codecool.quest.logic.actors;

public enum NPCType {
    WIZARD("wizard", new String[]{"Hello Adventurer!", "Please don't do that!", "Well I never!"}),
    GUARD("guard", new String[]{"For The God and the King!!", "Deus Vault!", "I used to be an adventurer like you, ", "Untill I took an arrow in the knee"});

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
