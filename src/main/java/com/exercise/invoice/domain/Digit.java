package com.exercise.invoice.domain;

import lombok.Getter;

import java.util.Optional;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * Represents a single digit
 * <p>
 * Display holds a string representation of the digit display
 * Value holds the character representation of the digit.
 * The character representation is exposed through toChar method.
 * In case of unknown digit value (represented by null value),
 * the digit is considered illegal, and will return '?' for toChar method
 */
@Getter
public class Digit {

    private static final char UNKNOWN_DIGIT = '?';

    private String display;
    private Character value;

    public Digit(String display, Character value) {
        this.display = display;
        this.value = value;
    }

    public boolean isLegal() {
        return value != null;
    }

    public char toChar() {
        return Optional.ofNullable(value).orElse(UNKNOWN_DIGIT);
    }

}
