package com.exercise.invoice.old.decoders.impl;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by itsik on 8/13/17.
 */
public class SevenSegmentsDisplayBufferedReader {

    private BufferedReader reader;

    public SevenSegmentsDisplayBufferedReader(BufferedReader reader) {
        this.reader = reader;
    }

    //TODO think whether invoice display includes the line separators or not
    public String readInvoiceNumber() throws IOException {
        StringBuilder invoiceNumberBuilder = new StringBuilder();
        for (int i=0; i < SevenSegmentsDisplayConstants.DIGIT_HEIGHT + 1 ; i++)
        {
            String line = reader.readLine();
            if (line == null) return null;
            invoiceNumberBuilder.append(line);
            if (i<SevenSegmentsDisplayConstants.DIGIT_HEIGHT) {
                invoiceNumberBuilder.append(System.lineSeparator());
            }
        }
        return invoiceNumberBuilder.toString();
    }
}
