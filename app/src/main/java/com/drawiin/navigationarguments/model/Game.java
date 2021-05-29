package com.drawiin.navigationarguments.model;

public class Game {
    private final String title;
    private final Gender gender;
    private final Platform platform;
    private final Modes mode;
    private final String producer;
    private final String launchDate;
    private final String image;

    public Game(String title, Gender gender, Platform platform, Modes mode, String producer, String launchDate, String image) {
        this.title = title;
        this.gender = gender;
        this.platform = platform;
        this.mode = mode;
        this.producer = producer;
        this.launchDate = launchDate;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public Gender getGender() {
        return gender;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Modes getMode() {
        return mode;
    }

    public String getProducer() {
        return producer;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public String getImage() {
        return image;
    }
}
