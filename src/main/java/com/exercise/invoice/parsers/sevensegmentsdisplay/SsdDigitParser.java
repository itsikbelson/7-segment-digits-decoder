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
 */
public class SsdDigitParser implements DigitParser {

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

    public void validate(String digitDisplay) {
        int digitLength = DIGIT_WIDTH * DIGIT_HEIGHT;
        if (digitDisplay == null || digitDisplay.length() != digitLength)
        {
            throw new IllegalArgumentException(MessageFormat.format("Display digit must be a {0} chars string",digitLength));
        }
    }

    @Override
    public Digit parse(String digitDisplay) {
        validate(digitDisplay);
        Character digitValue = digitsDecodingMap.get(digitDisplay);
        return new Digit(digitDisplay, digitValue);
    }
}
