package com.drawiin.navigationarguments.model;

import java.io.Serializable;

public enum Gender implements Serializable {
    BATTLE_ROYALE("Battle Royale"),
    FPS("FPS"),
    RPG("RPG"),
    RACE("Corrida"),
    FIGHT("Luta");


    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
