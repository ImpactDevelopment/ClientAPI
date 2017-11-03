package clientapi.command.executor.argument;

import clientapi.command.Command;
import clientapi.command.executor.ExecutionContext;
import clientapi.manage.Manager;
import clientapi.module.Module;

import java.util.Arrays;

/**
 * @author Brady
 * @since 11/3/2017 2:22 PM
 */
public final class CommandParser implements ArgumentParser<Command> {

    private final Manager<Command> commandManager;

    public CommandParser(Manager<Command> commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public final Command parse(ExecutionContext context, Class<?> type, String raw) {
        return commandManager.getData().stream().filter(cmd -> Arrays.stream(cmd.headers()).anyMatch(s -> s.equalsIgnoreCase(raw))).findFirst().orElse(null);
    }

    @Override
    public final boolean isTarget(Class<?> type) {
        return Command.class.isAssignableFrom(type);
    }
}
