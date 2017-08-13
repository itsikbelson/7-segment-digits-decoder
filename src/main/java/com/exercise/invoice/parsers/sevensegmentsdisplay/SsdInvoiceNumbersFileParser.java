package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.parsers.AbstractInvoiceNumbersBufferedParser;
import com.exercise.invoice.parsers.AbstractInvoiceNumbersFileParser;

import java.io.BufferedReader;

/**
 * Created by itsik on 8/13/17.
 */
public class SsdInvoiceNumbersFileParser extends AbstractInvoiceNumbersFileParser
{
    @Override
    protected AbstractInvoiceNumbersBufferedParser createBufferedParser(BufferedReader reader) {
        return new SsdInvoiceNumbersBufferedParser(reader);
    }
}
