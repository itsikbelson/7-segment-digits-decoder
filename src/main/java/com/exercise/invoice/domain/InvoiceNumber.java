package com.exercise.invoice.domain;

import lombok.Getter;

import java.util.List;

/**
 * Created by itsik on 8/13/17.
 * <p>
 * Represents an invoice number
 * <p>
 * Display holds a string representation of the invoice number
 * Digits hold the list of digit objects compounding the invoice number
 * The number representation is exposed through toString method.
 * In case of an illegal digit within the invoice number,
 * the number is considered illegal, and toString will have " ILLEGAL" suffix to the invoice number
 */
@Getter
public class InvoiceNumber {

    private static final String ILLEGAL_STR = "ILLEGAL";

    private String display;
    private List<Digit> digits;

    public InvoiceNumber(String display, List<Digit> digits) {
        this.display = display;
        this.digits = digits;
    }

    public boolean isLegal() {
        return digits.stream().allMatch(Digit::isLegal);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(digits.size());
        digits.forEach(digit -> builder.append(digit.toChar()));
        if (!isLegal()) {
            builder.append(" " + ILLEGAL_STR);
        }
        return builder.toString();
    }
}
