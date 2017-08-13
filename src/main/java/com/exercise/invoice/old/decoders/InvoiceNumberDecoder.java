package com.exercise.invoice.old.decoders;

import com.exercise.invoice.old.domain.DigitDisplay;

import java.util.List;

/**
 * Created by itsik on 8/13/17.
 *
 * Interface for decoding an invoice number
 */
public interface InvoiceNumberDecoder {
    String decode(String numberDisplay);

    List<DigitDisplay> parseDigits(String numberDisplay) throws IllegalArgumentException;
}
