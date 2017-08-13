package com.exercise.invoice.old.domain;

import com.exercise.invoice.old.decoders.InvoiceNumberDecoder;

/**
 * Created by itsik on 8/13/17.
 *
 * Represents the display of invoice number
 */
public class InvoiceNumberDisplay {

    private final InvoiceNumberDecoder decoder;
    private final String display;

    public InvoiceNumberDisplay(InvoiceNumberDecoder decoder, String invoiceNumberDisplay) {
        this.decoder = decoder;
        this.display = invoiceNumberDisplay;
    }

    public String toNumber()
    {
        return decoder.decode(display);
    }
}
