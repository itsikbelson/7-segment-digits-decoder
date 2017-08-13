package com.exercise.invoice.old.domain;

import com.exercise.invoice.old.decoders.DigitDecoder;

/**
 * Created by itsik on 8/12/17.
 *
 * Represents the display of a single digit
 *
 */
public class DigitDisplay {

    private DigitDecoder decoder;
    private String display;

    public DigitDisplay(DigitDecoder decoder, String digitDisplayString)
    {
        this.decoder = decoder;
        this.display = digitDisplayString;
        decoder.validate(display);
    }

    public String toString()
    {
        return decoder.format(display);
    }

    public String toPlainString()
    {
        return display;
    }

    //TODO think about using optional
    public char toDigit() {
        return decoder.decode(display);
    }
}
