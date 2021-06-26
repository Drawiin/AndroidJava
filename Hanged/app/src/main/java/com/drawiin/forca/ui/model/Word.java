package com.drawiin.forca.ui.model;

public class Word {
    private final int id;
    private final String word;

    public Word(int id, String word) {
        this.id = id;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getId() {
        return id;
    }
}
