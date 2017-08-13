package com.exercise.invoice.old.parsers;

import com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayBufferedReader;
import com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayInvoiceNumberDecoder;
import com.exercise.invoice.old.domain.InvoiceNumberDisplay;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by itsik on 8/11/17.
 *
 * This is the main class which is responsible for
 * decoding a file of invoice numbers in 7-segments-display
 * to the parsed invoice numbers.
 *
 * Example:
 *  _  _  _        _     _  _
 * |_ | || |  ||_| _|  ||_ |_
 * |_||_||_|  |  | _|  | _| _|
 *
 * will be decoded to:
 * 600143155
 */
public class InvoiceNumbersFileParser {
    public void decode(File inputFile, File outputFile) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(inputFile.toURI()))) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            SevenSegmentsDisplayBufferedReader reader = new SevenSegmentsDisplayBufferedReader(bufferedReader);
            SevenSegmentsDisplayInvoiceNumberDecoder decoder = new SevenSegmentsDisplayInvoiceNumberDecoder();
            String invoiceNumber;
            while ((invoiceNumber = reader.readInvoiceNumber()) != null)
            {
                InvoiceNumberDisplay invoiceNumberDisplay = new InvoiceNumberDisplay(decoder,invoiceNumber);
                String str = invoiceNumberDisplay.toNumber();
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        }
    }
}
