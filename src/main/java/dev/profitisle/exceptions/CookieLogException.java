package dev.profitisle.exceptions;

public class CookieLogException extends RuntimeException {
    public CookieLogException(String message, Throwable cause) {
        super(message, cause);
    }
}