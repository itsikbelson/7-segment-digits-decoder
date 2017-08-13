package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.Digit;

/**
 * Created by itsik on 8/13/17.
 */
public interface DigitParser {

    Digit parse(String digitDisplay);
}
