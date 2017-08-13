package com.exercise.invoice.old.decoders.impl;

import com.exercise.invoice.old.decoders.DigitDecoder;
import org.junit.Assert;
import org.junit.Test;

import static com.exercise.invoice.old.decoders.impl.SevenSegmentsDisplayConstants.DIGITS;

/**
 * Created by itsik on 8/11/17.
 *
 * Test Digit Decoder
 */
public class SevenSegmentsDisplayDigitDecoderTest {

    private DigitDecoder decoder = new SevenSegmentsDisplayDigitDecoder();

    @Test
    public void testDecodeDigits0To9()
    {
        checkDigitDecoding(0);
        checkDigitDecoding(1);
        checkDigitDecoding(2);
        checkDigitDecoding(3);
        checkDigitDecoding(4);
        checkDigitDecoding(5);
        checkDigitDecoding(6);
        checkDigitDecoding(7);
        checkDigitDecoding(8);
        checkDigitDecoding(9);
    }

    @Test
    public void testInvalidCharacters()
    {
        String invalidCharInDigit= "" +
                " * " +
                "|_|" +
                "|_|";
        checkDigitDecoding(invalidCharInDigit,'?');
    }

    @Test
    public void testUnknownDigit()
    {
        String unknownDigit= "" +
                "| |" +
                "|_|" +
                "|_|";
        checkDigitDecoding(unknownDigit,'?');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLength()
    {
        checkDigitDecoding(" - ",'?');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput()
    {
        checkDigitDecoding(null,'?');
    }

    private void checkDigitDecoding(String encodedDigit, char expectedDecodedDigit) {
        Assert.assertEquals(expectedDecodedDigit, decoder.decode(encodedDigit));
    }

    private void checkDigitDecoding(int digit) {
        checkDigitDecoding(DIGITS[digit],(char) ('0'+digit));
    }


}