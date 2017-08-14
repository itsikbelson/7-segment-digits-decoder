package com.exercise.invoice;

import com.exercise.invoice.parsers.InvoiceNumbersFileParser;
import com.exercise.invoice.parsers.sevensegmentsdisplay.SsdInvoiceNumbersFileParser;

import java.io.File;
import java.io.IOException;

/**
 * Created by itsik on 8/11/17.
 * <p>
 * Main entry point for the application
 * Accepts 2 arguments:
 * 1. Input file name
 * 2. Output file name
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 2)
        {
            System.err.println("Please provide input file name and output file name");
        }
        else
        {
            String inputFileName = args[0];
            String outputFileName = args[1];
            InvoiceNumbersFileParser parser = new SsdInvoiceNumbersFileParser();
            parser.parse(new File(inputFileName), outputFileName);
        }
    }
}
