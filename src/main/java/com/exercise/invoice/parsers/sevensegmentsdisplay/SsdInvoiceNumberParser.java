package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.parsers.InvoiceNumberParser;

import java.util.ArrayList;
import java.util.List;

import static com.exercise.invoice.parsers.sevensegmentsdisplay.SsdConstants.*;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * This class splits the digits in 7-segments display to a list of digit display strings.
 * <p>
 * Example:
 * _  _  _        _     _  _
 * |_ | || |  ||_| _|  ||_ |_
 * |_||_||_|  |  | _|  | _| _|
 * <p>
 * <p>
 * Item with index 5 (staring in 0) will hold:
 * _
 * _|
 * _|
 * <p>
 * <p>
 */
public class SsdInvoiceNumberParser extends InvoiceNumberParser {
    SsdInvoiceNumberParser() {
        super(new SsdDigitParser());
    }

    @Override
    protected List<String> splitDigitsDisplay(String invoiceNumberDisplay) {
        if (invoiceNumberDisplay == null || invoiceNumberDisplay.isEmpty()) {
            throw new IllegalArgumentException("Invoice number display cannot be null or empty");
        }

        //Limit = -1 to make sure we capture and validate the last blank line.
        //This also helps verifying there are no blank lines besides the last line
        String[] lines = invoiceNumberDisplay.split(System.lineSeparator(), -1);
        validate(lines);

        //Assuming all line are the same length.
        //No limitation on invoice number length
        List<String> digits = new ArrayList<>();
        int lineLength = lines[0].length();
        for (int digitStartPosition = 0;
             digitStartPosition < lineLength;
             digitStartPosition += DIGIT_WIDTH) {
            digits.add(parseDigit(lines, digitStartPosition));
        }
        return digits;
    }

    //Validations here are minimal, and intended to save parsing time where input is invalid.
    //Invoice number length is not validated as we do not limit it
    private void validate(String[] lines) throws IllegalArgumentException {
        int validLinesCount = INVOICE_NUMBER_HEIGHT;
        if (lines.length != validLinesCount) {
            throw new IllegalArgumentException(String.format("Invoice number must have %d lines, and has %d lines", validLinesCount, lines.length));
        }
        if (!lines[DIGIT_HEIGHT].isEmpty()) {
            throw new IllegalArgumentException("Invoice last line must be empty");
        }
    }

    private String parseDigit(String[] lines, int digitStartPosition) {
        StringBuilder digitBuilder = new StringBuilder(DIGIT_WIDTH * DIGIT_HEIGHT);

        //Last line (blank) is ignored
        try {
            for (int lineIndex = 0; lineIndex < DIGIT_HEIGHT; lineIndex++) {
                String substring = lines[lineIndex].substring(digitStartPosition, digitStartPosition + DIGIT_WIDTH);
                digitBuilder.append(substring);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Number display is invalid");
        }
        return digitBuilder.toString();
    }


}
