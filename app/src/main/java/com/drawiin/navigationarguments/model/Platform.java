package com.drawiin.navigationarguments.model;

public enum Platform {
    XBOX("XBOX"),
    PS4("PS4"),
    SWITCH("SWITCH"),
    ANDROID("ANDROID"),
    IOS("IOS");


    private final String name;

    Platform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
