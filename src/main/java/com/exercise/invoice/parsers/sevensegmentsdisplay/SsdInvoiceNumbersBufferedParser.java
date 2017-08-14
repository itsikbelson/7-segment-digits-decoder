package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.parsers.InvoiceNumbersBufferedParser;

import java.io.BufferedReader;
import java.io.IOException;

import static com.exercise.invoice.parsers.sevensegmentsdisplay.SsdConstants.DIGIT_HEIGHT;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * This class is responsible to read a single invoice number in 7-segments-display
 * from a buffered reader
 */
public class SsdInvoiceNumbersBufferedParser extends InvoiceNumbersBufferedParser {

    SsdInvoiceNumbersBufferedParser(BufferedReader reader) {
        super(reader, new SsdInvoiceNumberParser());
    }

    @Override
    protected String readInvoiceNumberDisplay() throws IOException {
        StringBuilder invoiceNumberBuilder = new StringBuilder();
        for (int i = 0; i < SsdConstants.INVOICE_NUMBER_HEIGHT; i++) {
            String line = reader.readLine();
            if (line == null) return null;
            //Invoice number includes line separators (all besides last blank line)
            invoiceNumberBuilder.append(line);
            if (i < DIGIT_HEIGHT) {
                invoiceNumberBuilder.append(System.lineSeparator());
            }
        }
        //
        return invoiceNumberBuilder.toString();

    }
}
