package dev.profitisle.commands;

import dev.profitisle.cli.CliParams;
import dev.profitisle.core.CookieLogCommand;
import dev.profitisle.core.CookieLogFileDataReader;
import dev.profitisle.core.CookieLogProcessor;

import java.util.Map;
import java.util.function.Function;

import static dev.profitisle.commands.CommandType.*;

/**
 * Factory class for creating Command instances based on the CommandType.
 *
 * @author Jawad Zaarour
 */
public class CommandFactory {

    private static final Map<CommandType, Function<Object[], Command>> COMMAND_FACTORY_MAP = Map.of(
            COOKIE, args -> new CookieLogCommand((CookieLogFileDataReader) args[0], (CookieLogProcessor) args[1]),
            HELP, args -> new HelpCommand((CliParams) args[0])
    );

    private CommandFactory() {
    }

    public static Command create(CommandType type, Object... args) {
        if (COMMAND_FACTORY_MAP.containsKey(type)) {
            return COMMAND_FACTORY_MAP.get(type).apply(args);
        }
        throw new UnsupportedOperationException("Unsupported command type: " + type);
    }
}