package dev.profitisle.exceptions;

public class NotFoundException extends CookieLogException {
    public NotFoundException(String filename) {
        super("File not found: " + filename, null);
    }
}
