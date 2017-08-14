package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.InvoiceNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * Abstract invoice number buffered parser.
 * Contains the logic of reading a single invoice number using the buffered reader
 * and convert to InvoiceNumber object.
 * Concrete classes should implement the logic of reading the invoice number display
 * based on the style.
 */
public abstract class InvoiceNumbersBufferedParser {

    private final InvoiceNumberParser parser;
    protected final BufferedReader reader;

    protected InvoiceNumbersBufferedParser(BufferedReader reader, InvoiceNumberParser parser) {
        this.reader = reader;
        this.parser = parser;
    }

    protected abstract String readInvoiceNumberDisplay() throws IOException;

    public InvoiceNumber parseInvoiceNumber() throws IOException {
        Optional<String> invoiceNumberDisplay = Optional.ofNullable(readInvoiceNumberDisplay());
        return invoiceNumberDisplay.map(parser::parse).orElse(null);
    }
}
