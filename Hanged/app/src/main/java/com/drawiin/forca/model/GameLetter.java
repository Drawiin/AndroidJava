package com.drawiin.forca.model;

import java.util.Objects;

public class GameLetter {
    final boolean discovered;
    final String letter;

    public GameLetter(boolean discovered, String letter) {
        this.discovered = discovered;
        this.letter = letter;
    }

    public GameLetter copyWithDiscovered(boolean discovered) {
        return new GameLetter(discovered, letter);
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public String getLetter() {
        return letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameLetter that = (GameLetter) o;
        return discovered == that.discovered &&
                Objects.equals(letter, that.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discovered, letter);
    }
}
