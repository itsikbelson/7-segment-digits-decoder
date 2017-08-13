package com.exercise.invoice.old.decoders.impl;

import com.exercise.invoice.old.domain.DigitDisplay;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

//import static com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayConstants.INVOICE_NUMBER_LENGTH;

/**
 * Created by itsik on 8/12/17.
 * <p>
 * Tesing digit parser out of a full invoice number
 */
public class SevenSegmentsDisplayInvoiceNumberDecoderTest {

    private SevenSegmentsDisplayInvoiceNumberDecoder decoder = new SevenSegmentsDisplayInvoiceNumberDecoder();

    @Test
    public void testParsingAllDigits() {
        String invoiceNumberDisplay =
                "" +
                        " _     _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "| |  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "|_|  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator();
        List<DigitDisplay> parsedDigits = decoder.parseDigits(invoiceNumberDisplay);
        for (int index = 0; index < 10; index++) {
            Assert.assertEquals(SevenSegmentsDisplayConstants.DIGITS[index], parsedDigits.get(index).toPlainString());
        }
    }

    @Test
    public void testValidInvoiceNumber123456789() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator();
        Assert.assertEquals("123456789", decoder.decode(invoiceNumberDisplay));
    }

    @Test
    public void testValidInvoiceNumber100000000() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _  _  _  _  _  _  _ " + System.lineSeparator() +
                        "  || || || || || || || || |" + System.lineSeparator() +
                        "  ||_||_||_||_||_||_||_||_|" + System.lineSeparator();
        Assert.assertEquals("100000000", decoder.decode(invoiceNumberDisplay));
    }

    @Test
    public void testInvoiceNumberWithIllegalCharacter87X543210() {
        String invoiceNumberDisplay =
                "" +
                        " _  _  X  _     _  _     _ " + System.lineSeparator() +
                        "|_|  || ||_ |_| _| _|  || |" + System.lineSeparator() +
                        "|_|  ||_| _|  | _||_   ||_|" + System.lineSeparator();
        Assert.assertEquals("87?543210", decoder.decode(invoiceNumberDisplay));
    }

    @Test
    public void testInvoiceNumberWith2IllegalCharactersX1234567X() {
        String invoiceNumberDisplay =
                "" +
                        " _     _  _     _  _  _  _ " + System.lineSeparator() +
                        " _|  | _| _||_||_ |_   || |" + System.lineSeparator() +
                        "|_|  ||_  _|  | _||_|  ||_ " + System.lineSeparator();
        Assert.assertEquals("?1234567?", decoder.decode(invoiceNumberDisplay));
    }

    @Test
    public void testInvoiceNumberWithAllIllegalCharactersXXXXXXXXX() {
        String invoiceNumberDisplay =
                "" +
                        "    _  X  _  -  _  _       " + System.lineSeparator() +
                        "|_| ||| ||A |_| _| _|  || |" + System.lineSeparator() +
                        "|_|  ||_| _|  ||_||_| _||_|" + System.lineSeparator();
        Assert.assertEquals("?????????", decoder.decode(invoiceNumberDisplay));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithInvalidLength() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _ _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_  ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_| ||_| _|" + System.lineSeparator();
        decoder.decode(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithDifferentLineLengths() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _ _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator();
        decoder.decode(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithInvalidLinesCount() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|";
        decoder.decode(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvoiceNumberWithNonBlankLastLine() {
        String invoiceNumberDisplay =
                "" +
                        "    _  _     _  _  _  _  _ " + System.lineSeparator() +
                        "  | _| _||_||_ |_   ||_||_|" + System.lineSeparator() +
                        "  ||_  _|  | _||_|  ||_| _|" + System.lineSeparator() +
                        " ";
        decoder.decode(invoiceNumberDisplay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInvoiceNumber() {
        decoder.decode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInvoiceNumber() {
        decoder.decode(null);
    }

}