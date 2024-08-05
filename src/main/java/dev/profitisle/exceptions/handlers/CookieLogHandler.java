package dev.profitisle.exceptions.handlers;

import dev.profitisle.exceptions.CookieLogException;
import dev.profitisle.exceptions.DateParsingException;
import dev.profitisle.exceptions.NotFoundException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.format.DateTimeParseException;

public class CookieLogHandler implements ExceptionHandler {

    @Override
    public void handle(Exception e) {
        switch (e) {
            case NoSuchFileException
                    ex -> throw new NotFoundException(ex.getFile());
            case IOException
                    ex  -> throw new CookieLogException("Error reading the file: " , ex);
            case DateTimeParseException
                    ex -> throw new DateParsingException(ex.getParsedString(), ex);
            case null,
                    default -> throw new CookieLogException("Error processing the request", e);
        }
    }
}
