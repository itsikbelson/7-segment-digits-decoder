package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.parsers.InvoiceNumbersBufferedParser;
import com.exercise.invoice.parsers.InvoiceNumbersFileParser;

import java.io.BufferedReader;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * This class is responsible for parsing a file
 * of invoice numbers in 7-segments-display
 * <p>
 * Example:
 * _  _  _        _     _  _
 * |_ | || |  ||_| _|  ||_ |_
 * |_||_||_|  |  | _|  | _| _|
 * <p>
 * will be decoded to:
 * 600143155
 */

public class SsdInvoiceNumbersFileParser extends InvoiceNumbersFileParser {
    @Override
    protected InvoiceNumbersBufferedParser createBufferedParser(BufferedReader reader) {
        return new SsdInvoiceNumbersBufferedParser(reader);
    }
}
