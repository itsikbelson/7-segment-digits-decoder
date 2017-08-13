package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.Digit;
import com.exercise.invoice.domain.InvoiceNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itsik on 8/13/17.
 */
public abstract class AbstractInvoiceNumberParser {

    private DigitParser digitParser;

    public AbstractInvoiceNumberParser(DigitParser digitParser) {
        this.digitParser = digitParser;
    }

    protected abstract List<String> splitDigits(String invoiceNumberDisplay);

    private List<Digit> parseDigits(String invoiceNumberDisplay)
    {
        List<Digit> digits = new ArrayList<>();
        List<String> digitsDisplay = splitDigits(invoiceNumberDisplay);
        digitsDisplay.forEach(digitDisplay -> digits.add(digitParser.parse(digitDisplay)));
        return digits;
    }

    public InvoiceNumber parse(String invoiceNumberDisplay) {
        List<Digit> digits = parseDigits(invoiceNumberDisplay);
        StringBuilder builder = new StringBuilder();
        boolean legal = true;
        for (Digit digit : digits)
        {
            builder.append(digit.toChar());
            if (!digit.isLegal())
            {
                legal = false;
            }
        }
        String invoiceNumberValue = builder.toString();
        return new InvoiceNumber(invoiceNumberDisplay, invoiceNumberValue, legal);
    }
}
