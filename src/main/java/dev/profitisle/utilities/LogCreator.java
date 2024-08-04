package dev.profitisle.utilities;

import dev.profitisle.cli.CliParams;
import dev.profitisle.core.CookieLogFileDataReader;
import dev.profitisle.core.CookieLogProcessor;

/**
 *
 * @author Jawad Zaarour
 */
public class LogCreator {

    public static CookieLogFileDataReader newCookieFileReader(String filePath, String date) {
        CliParams cliParams = new CliParams();
        cliParams.setFilename(filePath);
        cliParams.setDate(date);
        return new CookieLogFileDataReader(cliParams);
    }
}
