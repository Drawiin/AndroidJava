package com.drawiin.navigationarguments.model;

import java.io.Serializable;

public enum Platform implements Serializable {
    XBOX("Xbox"),
    PS4("PS4"),
    SWITCH("Switch"),
    ANDROID("Android"),
    IOS("IOS"),
    LINUX("Linux"),
    WINDOWS("Windows");


    private final String name;

    Platform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
