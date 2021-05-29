package com.drawiin.navigationarguments.model;

public enum Gender {
    BATTLE_ROYALE("Battle Royale"),
    FPS("FPS"),
    RPG("RPG"),
    RACE("Corrida"),
    FIGHT("LUTA");


    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
