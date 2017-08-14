package com.exercise.invoice.parsers.sevensegmentsdisplay;

import com.exercise.invoice.domain.InvoiceNumber;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by itsik on 8/14/17.
 *
 * Test invoice number parser
 */
public class SsdInvoiceNumberParserTest {

    private SsdInvoiceNumberParser parser = new SsdInvoiceNumberParser();
    @Test
    public void testParsingAllDigits() {
        String invoiceNumberDisplay =
                "" +
                        " _     _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "| |  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "|_|  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator();
        checkInvoiceNumberParsing("0123456789",invoiceNumberDisplay);
    }


    @Test
    public void testValidInvoiceNumber123456789() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator();
        checkInvoiceNumberParsing("123456789", invoiceNumberDisplay);
    }

    @Test
    public void testValidInvoiceNumber100000000() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _  _  _  _  _  _  _ " + System.lineSeparator() +
                        "  || || || || || || || || |" + System.lineSeparator() +
                        "  ||_||_||_||_||_||_||_||_|" + System.lineSeparator();
        checkInvoiceNumberParsing("100000000", invoiceNumberDisplay);
    }

    @Test
    public void testInvoiceNumberWithIllegalCharacter87X543210() {
        String invoiceNumberDisplay =
                "" +
                        " _  _  X  _     _  _     _ " + System.lineSeparator() +
                        "|_|  || ||_ |_| _| _|  || |" + System.lineSeparator() +
                        "|_|  ||_| _|  | _||_   ||_|" + System.lineSeparator();
        checkInvoiceNumberParsing("87?543210 ILLEGAL", invoiceNumberDisplay);
    }

    @Test
    public void testInvoiceNumberWith2IllegalCharactersX1234567X() {
        String invoiceNumberDisplay =
                "" +
                        " _     _  _     _  _  _  _ " + System.lineSeparator() +
                        " _|  | _| _||_||_ |_   || |" + System.lineSeparator() +
                        "|_|  ||_  _|  | _||_|  ||_ " + System.lineSeparator();
        checkInvoiceNumberParsing("?1234567? ILLEGAL", invoiceNumberDisplay);
    }

    @Test
    public void testInvoiceNumberWithAllIllegalCharactersXXXXXXXXX() {
        String invoiceNumberDisplay =
                "" +
                        "    _  X  _  -  _  _       " + System.lineSeparator() +
                        "|_| ||| ||A |_| _| _|  || |" + System.lineSeparator() +
                        "|_|  ||_| _|  ||_||_| _||_|" + System.lineSeparator();
        checkInvoiceNumberParsing("????????? ILLEGAL", invoiceNumberDisplay);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithInvalidLength() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _ _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_  ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_| ||_| _|" + System.lineSeparator();
        parseInvoiceNumber(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithDifferentLineLengths() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _ _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator();
        parseInvoiceNumber(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithInvalidLinesCount() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|";
        parseInvoiceNumber(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithNonBlankLastLine() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator() +
                        " ";
        parseInvoiceNumber(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInvoiceNumber() {
        parseInvoiceNumber("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInvoiceNumber() {
        parseInvoiceNumber(null);
    }

    private void checkInvoiceNumberParsing(String expectedInvoiceNumberString, String invoiceNumberDisplay) {
        InvoiceNumber invoiceNumber = parseInvoiceNumber(invoiceNumberDisplay);
        Assert.assertEquals(expectedInvoiceNumberString,invoiceNumber.toString());
    }

    private InvoiceNumber parseInvoiceNumber(String invoiceNumberDisplay) {
        return parser.parse(invoiceNumberDisplay);
    }

}