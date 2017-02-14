package me.zero.client.api.command.parse;

import me.zero.client.api.command.Command;
import me.zero.client.api.command.args.CommandArg;

import java.util.*;

import static me.zero.client.api.util.Messages.COMMAND_MISSING_ARGS;

/**
 * Used to handle command parsing
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public class CommandContext {

    private List<CommandArg<?>> arguments = new ArrayList<>();
    private List<String> lastBadSyntax = new ArrayList<>();
    private String[] args;

    @SuppressWarnings("unchecked")
    public CommandContext(Command command, String[] args) {
        this.args = args;
        this.arguments = Arrays.asList(command.arguments());
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
    public boolean hasArg(String label) {
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
    public Object get(String label) {
        CommandArg<?> argument = getArg(label);

        if (argument == null)
            return null;

        Object val = argument.getParser().parse(args[arguments.indexOf(argument)]);

        if (!argument.isOptional() && val == null)
            return null;

        return val;
    }

    /**
     * Checks whether or not all arguments have been satisfied.
     *
     * @since 1.0
     *
     * @return True/False depending on argument satisfaction
     */
    public boolean isComplete() {
        lastBadSyntax.clear();
        arguments.stream().filter(arg -> !hasArg(arg.getLabel()))
                .forEach(arg -> lastBadSyntax.add(String.format(COMMAND_MISSING_ARGS, arg.getLabel(), arg.getType())));

        return lastBadSyntax.size() <= 0;
    }

    /**
     * @since 1.0
     *
     * @return The last error stack
     */
    public List<String> getError() {
        return this.lastBadSyntax;
    }
}
