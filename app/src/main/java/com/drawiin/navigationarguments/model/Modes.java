package com.drawiin.navigationarguments.model;

public enum Modes {
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
