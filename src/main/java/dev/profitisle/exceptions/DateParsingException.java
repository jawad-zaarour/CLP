package dev.profitisle.exceptions;

public class DateParsingException extends CookieLogException {
    public DateParsingException(String date, Throwable cause) {
        super("Error parsing the date: " + date, cause);
    }
}