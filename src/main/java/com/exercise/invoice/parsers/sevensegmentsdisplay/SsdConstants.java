package com.exercise.invoice.parsers.sevensegmentsdisplay;

/**
 * Created by itsik on 8/11/17.
 * <p>
 * This class holds string constants representing 7-segments digits display
 */
public interface SsdConstants {

/* Note: I could have changed this with a style interface,
   that can have different implementation,
   but thought that at this time it would be over-engineering */

    int DIGIT_WIDTH = 3;
    int DIGIT_HEIGHT = 3;

    int INVOICE_NUMBER_HEIGHT = DIGIT_HEIGHT + 1;

    // empty string in the beginning of each digit is used only to overcome auto-layouting in IDE
    String[] DIGITS =
            {
                    "" +
                            " _ " +
                            "| |" +
                            "|_|",
                    "" +
                            "   " +
                            "  |" +
                            "  |",
                    "" +
                            " _ " +
                            " _|" +
                            "|_ ",
                    "" +
                            " _ " +
                            " _|" +
                            " _|",
                    "" +
                            "   " +
                            "|_|" +
                            "  |",
                    "" +
                            " _ " +
                            "|_ " +
                            " _|",
                    "" +
                            " _ " +
                            "|_ " +
                            "|_|",
                    "" +
                            " _ " +
                            "  |" +
                            "  |",
                    "" +
                            " _ " +
                            "|_|" +
                            "|_|",
                    "" +
                            " _ " +
                            "|_|" +
                            " _|"};


}
