package dev.profitisle.core;

import dev.profitisle.cli.CliParams;
import dev.profitisle.domains.Cookie;
import dev.profitisle.domains.CookieLog;
import dev.profitisle.utilities.DateTimeUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.profitisle.utilities.DateTimeUtil.getDate;

/**
 * Read cookie logs from a file based on a specified date.
 * <p>
 * Worst case (date in last lines): &Theta(n), where n is the number of lines in the file
 *
 * @author Jawad Zaarour
 */
public class CookieLogFileDataReader implements DataReader<CookieLog> {

    private final CliParams cliParams;

    private static final int HEADER = 1;
    private static final String SPLITTER = ",";

    public CookieLogFileDataReader(CliParams cliParams) {
        this.cliParams = cliParams;
    }

    @Override
    public CookieLog get() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(cliParams.filename()))) {
            LocalDate date = getDate(cliParams.date());

            List<Cookie> cookies = lines
                    .skip(HEADER)
                    .map(line -> line.split(SPLITTER))
                    .filter(parts -> parts.length == 2)
                    .map(parts -> new Cookie(parts[0], DateTimeUtil.parseToDate(parts[1])))
                    .takeWhile(cookie -> !cookie.cookieDate().isBefore(date))
                    .filter(cookie -> cookie.cookieDate().equals(date))
                    .collect(Collectors.toList());

            return new CookieLog(cookies);
        }
    }
}
