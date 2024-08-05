package dev.profitisle.commands;

import dev.profitisle.cli.CliParams;
import dev.profitisle.core.CookieLogFileDataReader;
import dev.profitisle.core.CookieLogProcessor;

/**
 *
 * @author Jawad Zaarour
 */
public class CommandCreator {

    /**
     * Determines the command to execute based on the provided CLI parameters.
     *
     */
    public static Command getCommand(CliParams cliParams) {
        if (cliParams.help()) {
            return CommandFactory.create(CommandType.HELP, cliParams);
        } else {
            return CommandFactory.create(CommandType.COOKIE, new CookieLogFileDataReader(cliParams), new CookieLogProcessor());
        }
    }
}
