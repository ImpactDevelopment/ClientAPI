package me.zero.client.api.command.parse;

import me.zero.client.api.command.Command;

import java.util.*;

import static me.zero.client.api.util.Messages.*;

/**
 * Used to handle command parsing
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public final class CommandContext {

    /**
     * The list of arguments
     */
    private final List<CommandArg> arguments = new ArrayList<>();

    /**
     * The error stack, updated when a command isn't executed properly
     */
    private final List<String> errorStack = new ArrayList<>();

    /**
     * The raw args that were provided by the user
     */
    private String[] args;

    @SuppressWarnings("unchecked")
    public CommandContext(Command command, String[] args) {
        this.args = args;
        this.arguments.addAll(Arrays.asList(command.arguments()));
    }

    /**
     * Checks if there is a value corresponding with the argument
     * associated with the specified label.
     *
     * @since 1.0
     *
     * @param label The label
     * @return True if there is a value, false if not
     */
    public final boolean hasArg(String label) {
        return get(label) != null;
    }

    /**
     * Returns the CommandArg that uses the specified label
     *
     * @since 1.0
     *
     * @param label The label
     * @return The CommandArg from the label
     */
    private CommandArg<?> getArg(String label) {
        return arguments.stream().filter(arg -> arg.getLabel().equalsIgnoreCase(label)).findFirst().orElse(null);
    }

    /**
     * Gets the object value from the specified label
     *
     * @since 1.0
     *
     * @param label The Label
     * @return The object
     */
    public final Object get(String label) {
        CommandArg<?> argument = getArg(label);

        if (argument == null)
            return null;

        try {
            return argument.getParser().apply(args[arguments.indexOf(argument)]);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Checks whether or not all arguments have been satisfied.
     *
     * @since 1.0
     *
     * @return True/False depending on argument satisfaction
     */
    public final boolean isComplete() {
        errorStack.clear();
        arguments.stream().filter(arg -> !hasArg(arg.getLabel()))
                .forEach(arg -> errorStack.add(String.format(COMMAND_MISSING_ARGS, arg.getLabel(), arg.getType())));

        return errorStack.size() <= 0;
    }

    /**
     * @since 1.0
     *
     * @return The last error stack
     */
    public final List<String> getError() {
        return this.errorStack;
    }
}
