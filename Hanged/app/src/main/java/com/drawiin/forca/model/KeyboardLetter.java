package com.drawiin.forca.model;

import java.util.Objects;

public class KeyboardLetter {
    private final String letter;
    private final KeyboardLetterState state;

    public KeyboardLetter(String letter, KeyboardLetterState state) {
        this.letter = letter;
        this.state = state;
    }

    public String getLetter() {
        return letter;
    }

    public KeyboardLetterState getState() {
        return state;
    }

    public KeyboardLetter copyWithState(KeyboardLetterState state) {
        return new KeyboardLetter(letter, state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyboardLetter that = (KeyboardLetter) o;
        return Objects.equals(letter, that.letter) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, state);
    }
}
