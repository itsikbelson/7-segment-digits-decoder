package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.Digit;
import com.exercise.invoice.domain.InvoiceNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * Abstract invoice number parser.
 * Contains the logic of parsing invoice number display,
 * by looping through the digits display, parsing each digit,
 * and concat the parsed digits to a number.
 * Concrete classes should provide the logic of splitting invoice number display
 * to a list of digits display
 */
public abstract class InvoiceNumberParser {

    private DigitParser digitParser;

    protected InvoiceNumberParser(DigitParser digitParser) {
        this.digitParser = digitParser;
    }

    protected abstract List<String> splitDigitsDisplay(String invoiceNumberDisplay);

    private List<Digit> parseDigits(String invoiceNumberDisplay) {
        List<Digit> digits = new ArrayList<>();
        List<String> digitsDisplay = splitDigitsDisplay(invoiceNumberDisplay);
        digitsDisplay.forEach(digitDisplay -> digits.add(digitParser.parse(digitDisplay)));
        return digits;
    }

    public InvoiceNumber parse(String invoiceNumberDisplay) {
        List<Digit> digits = parseDigits(invoiceNumberDisplay);
        return new InvoiceNumber(invoiceNumberDisplay, digits);
    }
}
