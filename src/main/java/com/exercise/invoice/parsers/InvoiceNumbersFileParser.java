package com.exercise.invoice.parsers;

import com.exercise.invoice.domain.InvoiceNumber;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * Abstract invoice numbers file parser.
 * Contains the logic to read through the file for invoice numbers.
 * Concrete classes should define the buffered parser,
 * which parses invoice numbers from the file.
 */
public abstract class InvoiceNumbersFileParser {

    protected abstract InvoiceNumbersBufferedParser createBufferedParser(BufferedReader reader);

    public File parse(File inputFile, String outputFileName) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(inputFile.toPath())) {
            InvoiceNumbersBufferedParser bufferedParser = createBufferedParser(bufferedReader);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                InvoiceNumber invoiceNumber;
                while ((invoiceNumber = bufferedParser.parseInvoiceNumber()) != null) {
                    writer.write(invoiceNumber.toString());
                    writer.newLine();
                }
            }
        }
        return new File(outputFileName);
    }
}
