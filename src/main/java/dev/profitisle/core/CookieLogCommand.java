package dev.profitisle.core;

import dev.profitisle.cli.CliParams;
import dev.profitisle.commands.Command;
import dev.profitisle.domains.CookieLog;
import dev.profitisle.exceptions.handlers.CookieLogHandler;

import java.util.List;

/**
 * * @author Jawad Zaarour
 */
public class CookieLogCommand implements Command {
    private final CliParams cliParams;

    public CookieLogCommand(CliParams cliParams) {
        this.cliParams = cliParams;
    }

    @Override
    public void execute() {
        try {
            var dataSource = new CookieLogFileDataReader(cliParams);
            CookieLog log = dataSource.get();

            var processor = new CookieLogProcessor();
            List<String> mostActiveCookies = processor.process(log);

            // Print the most active cookies
            mostActiveCookies.forEach(System.out::println);
        } catch (Exception e) {
            new CookieLogHandler(cliParams).handle(e);
        }
    }
}