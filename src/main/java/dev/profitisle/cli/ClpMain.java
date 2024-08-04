package dev.profitisle.cli;

import dev.profitisle.commands.Command;
import dev.profitisle.exceptions.CookieLogException;
import dev.profitisle.commands.CommandCreator;
import picocli.CommandLine;

/**
 * The entry point for the CLP application.
 *
 * @author Jawad Zaarour
 */
public class ClpMain {

    public static void main(String... args) {
        CliParams cliParams = new CliParams();
        CommandLine commandLine = new CommandLine(cliParams);

        try {
            commandLine.parseArgs(args);
            Command command = CommandCreator.getCommand(cliParams);
            command.execute();
        }
        catch (CommandLine.ParameterException | CookieLogException e) {
            System.err.println(e.getMessage());
        }

    }
}
