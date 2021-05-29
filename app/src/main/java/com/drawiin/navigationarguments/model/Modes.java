package com.drawiin.navigationarguments.model;

import java.io.Serializable;

public enum Modes implements Serializable {
    SINGLE("Single Player"),
    MULTI("Multi Player");


    private final String name;

    Modes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
