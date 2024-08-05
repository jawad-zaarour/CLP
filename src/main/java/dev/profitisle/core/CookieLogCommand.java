package dev.profitisle.core;

import dev.profitisle.commands.Command;
import dev.profitisle.domains.CookieLog;
import dev.profitisle.exceptions.handlers.CookieLogHandler;

import java.util.List;

/**
 * Command class for processing cookie logs.
 *
 * @author Jawad Zaarour
 */
public class CookieLogCommand implements Command {

    private final DataReader<CookieLog> reader;
    private final DataProcessor<CookieLog, List<String>> processor;

    public CookieLogCommand(DataReader<CookieLog> reader, DataProcessor<CookieLog, List<String>> processor) {
        this.reader = reader;
        this.processor = processor;
    }

    @Override
    public void execute() {
        try {
            List<String> mostActiveCookies = processor.process(reader.get());

            // Print the most active cookies
            mostActiveCookies.forEach(System.out::println);
        } catch (Exception e) {
            new CookieLogHandler().handle(e);
        }
    }
}