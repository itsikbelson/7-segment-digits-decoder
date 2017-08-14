package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.parsers.InvoiceNumbersFileParser;
import com.exercise.invoice.parsers.sevensegmentsdisplay.SsdInvoiceNumbersFileParser;
import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;

/**
 * Created by itsik on 8/11/17.
 * <p>
 * Main test which verifies output files are the same as expected for the exercise
 */
public class SsdInvoiceNumbersFileParserTest {

    private InvoiceNumbersFileParser fileParser = new SsdInvoiceNumbersFileParser();

    @Test
    public void testQ1ValidFile() throws IOException {
        checkFileParsing("q1a-valid/input_Q1a.txt", "q1a-valid/output_Q1a.txt");
    }

    @Test
    public void testQ2IllegalFile() throws IOException {
        checkFileParsing("q1b-illegal/input_Q1b.txt", "q1b-illegal/output_Q1b.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFile() throws IOException {
        parseFile("invalid_file.txt");
    }

    private void checkFileParsing(String inputFileName, String expectedOutputFileName) throws IOException {
        File actualOutputFile = parseFile(inputFileName);
        File expectedOutputFile = getTestResource(expectedOutputFileName);
        FileAssert.assertEquals(expectedOutputFile, actualOutputFile);
    }

    private File parseFile(String inputFileName) throws IOException {
        File inputFile = getTestResource(inputFileName);
        String outputFileName = inputFile.getParent() + "/test_output.txt";
        return fileParser.parse(inputFile, outputFileName);
    }


    private File getTestResource(String filename) {
        try {
            URL resource = getClass().getResource("/" + filename);
            URI uri = resource.toURI();
            return new File(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
