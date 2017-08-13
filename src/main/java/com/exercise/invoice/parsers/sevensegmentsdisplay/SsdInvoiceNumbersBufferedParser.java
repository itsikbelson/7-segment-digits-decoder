package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayConstants;
import com.exercise.invoice.parsers.AbstractInvoiceNumbersBufferedParser;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by itsik on 8/13/17.
 */
public class SsdInvoiceNumbersBufferedParser extends AbstractInvoiceNumbersBufferedParser {

    public SsdInvoiceNumbersBufferedParser(BufferedReader reader) {
        super(reader, new SsdInvoiceNumberParser());
    }

    @Override
    protected String readInvoiceNumber() throws IOException {
        StringBuilder invoiceNumberBuilder = new StringBuilder();
        for (int i = 0; i < SsdConstants.DIGIT_HEIGHT + 1 ; i++)
        {
            String line = reader.readLine();
            if (line == null) return null;
            invoiceNumberBuilder.append(line);
            if (i<SsdConstants.DIGIT_HEIGHT) {
                invoiceNumberBuilder.append(System.lineSeparator());
            }
        }
        return invoiceNumberBuilder.toString();

    }
}
