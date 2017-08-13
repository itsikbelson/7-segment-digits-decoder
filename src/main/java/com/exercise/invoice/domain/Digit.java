package com.exercise.invoice.domain;

import lombok.Getter;

import java.util.Optional;

/**
 * Created by itsik on 8/13/17.
 */
@Getter
public class Digit {

    private static final char UNKNOWN_DIGIT = '?';

    private String display;
    private Character value;

    public Digit(String display, Character value)
    {
        this.display = display;
        this.value = value;
    }

    public boolean isLegal()
    {
        return value != null;
    }

    public char toChar()
    {
        if (value != null) {
            return value;
        }
        else
        {
            return UNKNOWN_DIGIT;
        }
    }

}
