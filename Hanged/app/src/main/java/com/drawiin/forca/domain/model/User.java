package com.drawiin.forca.domain.model;

public class User {
    private final int id;
    private final String name;
    private final String photo;

    public User(int id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
