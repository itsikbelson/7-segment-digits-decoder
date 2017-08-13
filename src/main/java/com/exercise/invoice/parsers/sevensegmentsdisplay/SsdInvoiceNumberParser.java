package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.parsers.AbstractInvoiceNumberParser;

import java.util.ArrayList;
import java.util.List;

import static com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayConstants.DIGIT_HEIGHT;
import static com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayConstants.DIGIT_WIDTH;

/**
 * Created by itsik on 8/13/17.
 */
public class SsdInvoiceNumberParser extends AbstractInvoiceNumberParser {
    public SsdInvoiceNumberParser() {
        super(new SsdDigitParser());
    }

    @Override
    protected List<String> splitDigits(String invoiceNumberDisplay) {
        if (invoiceNumberDisplay == null || invoiceNumberDisplay.isEmpty())
        {
            throw new IllegalArgumentException("Invoice number display cannot be null or empty");
        }

        //Make sure we capture and validate the last blank line.
        //This also helps verifying there are no blank lines besides the last line
        String[] lines = invoiceNumberDisplay.split(System.lineSeparator(),-1);
        validate(lines);

        //Assuming all line are the same length.
        //No limitation on invoice number length
        int lineLength = lines[0].length();

        List<String> digits = new ArrayList<>();

        int digitStartPosition = 0;
        while (digitStartPosition < lineLength) {
            digits.add(parseDigit(lines, digitStartPosition));
            digitStartPosition += DIGIT_WIDTH;
        }
        return digits;
    }

    //Validations here are minimal, and intended to save parsing time where input is invalid.
    //Invoice number length is not validated as we do not limit it
    private void validate(String[] lines) throws IllegalArgumentException
    {
        int validLinesCount = DIGIT_HEIGHT + 1;
        if (lines.length != validLinesCount)
        {
            throw new IllegalArgumentException(String.format("Invoice number must have %d lines, and has %d lines", validLinesCount,lines.length));
        }
        if (!lines[DIGIT_HEIGHT].isEmpty())
        {
            throw new IllegalArgumentException("Invoice last line must be empty");
        }
    }

    private String parseDigit(String[] lines, int digitStartPosition) {
        StringBuilder digitBuilder = new StringBuilder(DIGIT_WIDTH * DIGIT_HEIGHT);

        //Last line (blank) is ignored
        try {
            for (int lineIndex = 0; lineIndex < lines.length - 1; lineIndex++) {
                String substring = lines[lineIndex].substring(digitStartPosition, digitStartPosition + DIGIT_WIDTH);
                digitBuilder.append(substring);
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Number display is invalid");
        }
        return digitBuilder.toString();
    }


}
