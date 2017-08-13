package com.exercise.invoice.old.decoders;

/**
 * Created by itsik on 8/13/17.
 *
 * Interface for decoding a single digit
 */
public interface DigitDecoder {
    char decode(String digitDisplay);

    void validate(String digitDisplay);

    String format(String digitDisplay);
}
