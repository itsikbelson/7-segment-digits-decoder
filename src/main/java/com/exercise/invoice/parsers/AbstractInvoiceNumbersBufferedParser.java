package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.InvoiceNumber;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by itsik on 8/13/17.
 */
public abstract class AbstractInvoiceNumbersBufferedParser {

    private final AbstractInvoiceNumberParser parser;
    protected final BufferedReader reader;

    public AbstractInvoiceNumbersBufferedParser(BufferedReader reader, AbstractInvoiceNumberParser parser) {
        this.reader = reader;
        this.parser = parser;
    }

    protected abstract String readInvoiceNumber() throws IOException;

    public InvoiceNumber parseInvoiceNumber() throws IOException {
        String invoiceNumberDisplay = readInvoiceNumber();
        //TODO use optional
        if (invoiceNumberDisplay == null)
        {
            return null;
        }
        return parser.parse(invoiceNumberDisplay);
    }
}
