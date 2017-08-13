package com.exercise.invoice.domain;

import lombok.Getter;

/**
 * Created by itsik on 8/13/17.
 */
@Getter
public class InvoiceNumber {

    private static final String ILLEGAL_STR = "ILLEGAL";

    private String display;
    private String value;
    private boolean legal;

    public InvoiceNumber(String display, String value, boolean legal) {
        this.display = display;
        this.value = value;
        this.legal = legal;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder(value);
        if (!legal)
        {
            builder.append(" " + ILLEGAL_STR);
        }
        return builder.toString();
    }
}
