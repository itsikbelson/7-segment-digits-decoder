package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.InvoiceNumber;
import com.exercise.invoice.parsers.sevensegmentsdisplay.SsdInvoiceNumbersBufferedParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by itsik on 8/13/17.
 */
public abstract class AbstractInvoiceNumbersFileParser {

    protected abstract AbstractInvoiceNumbersBufferedParser createBufferedParser(BufferedReader reader);

    public File parse(File inputFile, String outputFileName) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(inputFile.toPath())) {
            AbstractInvoiceNumbersBufferedParser bufferedParser = createBufferedParser(bufferedReader);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            InvoiceNumber invoiceNumber;
            while ((invoiceNumber = bufferedParser.parseInvoiceNumber()) != null)
            {
                writer.write(invoiceNumber.toString());
                writer.newLine();
            }
            writer.close();
        }
        return new File(outputFileName);
    }
}
