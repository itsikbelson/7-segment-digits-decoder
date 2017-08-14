package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.domain.Digit;
import com.exercise.invoice.parsers.DigitParser;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.exercise.invoice.parsers.sevensegmentsdisplay.SsdConstants.*;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * This class parses a string which represents a single digit in 7-segment-display
 * to a digit object, which includes the character representing the digit
 * <p>
 * Input string always include 9 chars, 3 chars per line.
 * <p>
 * Example:
 * _
 * |_
 * |_|
 * <p>
 * is represented as : " _ |_ |_|"
 * <p>
 * The parsed character will be '6'
 * <p>
 * In case of unrecognized digit, the parsed character will be null,
 * and the digit will be considered illegal
 * It's the digit class responsibility to define how to handle illegal digits
 */
public class SsdDigitParser extends DigitParser {

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

    private void validate(String digitDisplay) {
        int digitLength = DIGIT_WIDTH * DIGIT_HEIGHT;
        if (digitDisplay == null || digitDisplay.length() != digitLength) {
            throw new IllegalArgumentException(MessageFormat.format("Display digit must be a {0} chars string", digitLength));
        }
    }

    @Override
    public Digit parse(String digitDisplay) {
        validate(digitDisplay);
        Character digitValue = digitsDecodingMap.get(digitDisplay);
        return new Digit(digitDisplay, digitValue);
    }
}
