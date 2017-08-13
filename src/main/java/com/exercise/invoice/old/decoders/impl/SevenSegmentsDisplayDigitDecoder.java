package com.exercise.invoice.old.decoders.impl;

import com.exercise.invoice.old.decoders.DigitDecoder;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayConstants.*;

/**
 * Created by itsik on 8/11/17.
 * <p>
 * This class decodes a string which represents a single digit in 7-segment-display to a single digit char
 * <p>
 * Input string always include 9 chars, 3 chars per line.
 * <p>
 * Example:
 *  _
 * |_
 * |_|
 * <p>
 * is represented as : " _ |_ |_|"
 * <p>
 * The output will be '6'
 * <p>
 * In case of unrecognized digit, a default character will be returned - '?'
 */
public class SevenSegmentsDisplayDigitDecoder implements DigitDecoder {

    private static final char UNKNOWN_DIGIT = '?';

    private final Map<String, Character> digitsDecodingMap =
            Collections.unmodifiableMap(new HashMap<String, Character>() {
                {
                    put(DIGITS[0], '0');
                    put(DIGITS[1], '1');
                    put(DIGITS[2], '2');
                    put(DIGITS[3], '3');
                    put(DIGITS[4], '4');
                    put(DIGITS[5], '5');
                    put(DIGITS[6], '6');
                    put(DIGITS[7], '7');
                    put(DIGITS[8], '8');
                    put(DIGITS[9], '9');
                }
            });


    @Override
    public char decode(String digitDisplay) {
        validate(digitDisplay);
        return digitsDecodingMap.getOrDefault(digitDisplay,UNKNOWN_DIGIT);
    }

    @Override
    public void validate(String digitDisplay) {
        int digitLength = DIGIT_WIDTH * DIGIT_HEIGHT;
        if (digitDisplay == null || digitDisplay.length() != digitLength)
        {
            throw new IllegalArgumentException(MessageFormat.format("Display digit must be a {0} chars string",digitLength));
        }
    }

    @Override
    public String format(String digitDisplay)
    {
        StringBuilder displayBuilder = new StringBuilder();

        for (int index = 0; index < DIGIT_HEIGHT; index++) {
            displayBuilder
                    .append(digitDisplay.substring(index * DIGIT_WIDTH, index * DIGIT_WIDTH + DIGIT_WIDTH))
                    .append(System.lineSeparator());
        }
        return displayBuilder.toString();
    }

}