package com.exercise.invoice;

import com.exercise.invoice.parsers.AbstractInvoiceNumbersFileParser;
import com.exercise.invoice.parsers.sevensegmentsdisplay.SsdInvoiceNumbersFileParser;
import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;

/**
 * Created by itsik on 8/11/17.
 * <p>
 * Main test which verifies output files are the same as expected for the exercise
 */
public class SsdInvoiceNumbersFileParserTest {

    private AbstractInvoiceNumbersFileParser fileParser = new SsdInvoiceNumbersFileParser();

    @Test
    public void testQ1ValidFile() throws IOException {
        checkFileParsing("q1a-valid/input_Q1a.txt", "q1a-valid/output_Q1a.txt");
    }

    @Test
    public void testQ2IllegalFile() throws IOException {
        checkFileParsing("q1b-illegal/input_Q1b.txt", "q1b-illegal/output_Q1b.txt");
    }

    @Test
    public void testMissingFile() {
        throw new RuntimeException("TBD");
    }

    @Test
    public void testInvalidFile() {
        throw new RuntimeException("TBD");
    }

    private void checkFileParsing(String inputFileName, String expectedOutputFileName) throws IOException {
        File inputFile = getTestResource(inputFileName);
        String outputFileName = inputFile.getParent() + "/test_output.txt";
        File actualOutputFile = fileParser.parse(inputFile, outputFileName);
        File expectedOutputFile = getTestResource(expectedOutputFileName);
        FileAssert.assertEquals(expectedOutputFile, actualOutputFile);
    }


    private File getTestResource(String filename) {
        try {
            URL resource = getClass().getResource("/" + filename);
            URI uri = resource.toURI();
            return new File(uri);
        } catch (Throwable e) {
            throw new RuntimeException(MessageFormat.format("Unable to get file {0}", filename, e));
        }
    }
}
