package dev.profitisle.exceptions.handlers;

import dev.profitisle.cli.CliParams;
import dev.profitisle.exceptions.CookieLogException;
import dev.profitisle.exceptions.DateParsingException;
import dev.profitisle.exceptions.handlers.ExceptionHandler;
import dev.profitisle.exceptions.NotFoundException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.format.DateTimeParseException;

public class CookieLogHandler implements ExceptionHandler {

    private final CliParams cliParams;

    public CookieLogHandler(CliParams cliParams) {
        this.cliParams = cliParams;
    }

    @Override
    public void handle(Exception e) {
        switch (e) {
            case NoSuchFileException
                    ignored -> throw new NotFoundException(cliParams.filename());
            case IOException
                    ignored -> throw new CookieLogException("Error reading the file: " + cliParams.filename(), e);
            case DateTimeParseException
                    ignored -> throw new DateParsingException(cliParams.date(), e);
            case null,
                    default -> throw new CookieLogException("Error processing the request", e);
        }
    }
}
