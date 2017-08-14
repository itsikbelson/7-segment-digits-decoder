package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.Digit;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * Abstract digit parser.
 * Concrete classes shoule provide the logic of converting digit display to a digit object
 */
public abstract class DigitParser {

    public abstract Digit parse(String digitDisplay);
}
