package dev.profitisle.commands;

import dev.profitisle.cli.CliParams;
import picocli.CommandLine;

/**
 *
 * @author Jawad Zaarour
 */
public class HelpCommand implements Command {

    private final CliParams cliParams;

    public HelpCommand(CliParams cliParams) {
        this.cliParams = cliParams;
    }

    @Override
    public void execute() {
        CommandLine.usage(cliParams, System.out);
    }
}
