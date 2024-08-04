package dev.profitisle.commands;

import dev.profitisle.cli.CliParams;
import dev.profitisle.core.CookieLogCommand;

import java.util.Map;

import static dev.profitisle.commands.CommandType.*;

/**
 * Factory class for creating Command instances based on the CommandType.
 *
 * @author Jawad Zaarour
 */
public class CommandFactory {

    private static final Map<CommandType, CommandInitializer> COMMAND_FACTORY_MAP = Map.of(
            COOKIE, CookieLogCommand::new,
            HELP, HelpCommand::new
    );

    private CommandFactory() {
    }

    public static Command create(CommandType type, CliParams cliParams) {
        if (COMMAND_FACTORY_MAP.containsKey(type)) {
            return COMMAND_FACTORY_MAP.get(type).initialize(cliParams);
        }
        throw new UnsupportedOperationException();
    }

    private interface CommandInitializer {
        Command initialize(CliParams cliParams);
    }
}
