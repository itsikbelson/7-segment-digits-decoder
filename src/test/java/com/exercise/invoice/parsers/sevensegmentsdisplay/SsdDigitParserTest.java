package com.exercise.invoice.parsers.sevensegmentsdisplay;

import org.junit.Assert;
import org.junit.Test;

import static com.exercise.invoice.parsers.sevensegmentsdisplay.SsdConstants.DIGITS;

/**
 * Created by itsik on 8/14/17.
 *
 * Test digit parser
 */
public class SsdDigitParserTest {

    private SsdDigitParser parser = new SsdDigitParser();

    @Test
    public void testDecodeDigits0To9()
    {
        checkDigitParsing(0);
        checkDigitParsing(1);
        checkDigitParsing(2);
        checkDigitParsing(3);
        checkDigitParsing(4);
        checkDigitParsing(5);
        checkDigitParsing(6);
        checkDigitParsing(7);
        checkDigitParsing(8);
        checkDigitParsing(9);
    }

    @Test
    public void testInvalidCharacters()
    {
        String invalidCharInDigit= "" +
                " * " +
                "|_|" +
                "|_|";
        checkDigitParsing(invalidCharInDigit,'?');
    }

    @Test
    public void testUnknownDigit()
    {
        String unknownDigit= "" +
                "| |" +
                "|_|" +
                "|_|";
        checkDigitParsing(unknownDigit,'?');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLength()
    {
        checkDigitParsing(" - ",'?');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput()
    {
        checkDigitParsing(null,'?');
    }

    private void checkDigitParsing(String digitDisplay, char expectedDigitValue) {
        Assert.assertEquals(expectedDigitValue, parser.parse(digitDisplay).toChar());
    }

    private void checkDigitParsing(int digit) {
        checkDigitParsing(DIGITS[digit],(char) ('0'+digit));
    }

}