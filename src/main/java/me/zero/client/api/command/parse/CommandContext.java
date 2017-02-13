package me.zero.client.api.command.parse;

import me.zero.client.api.command.Command;
import me.zero.client.api.command.args.CommandArg;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Used to handle command parsing
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public class CommandContext {

    private List<CommandArg<?>> arguments = new ArrayList<>();
    private Map<String, Object> keys = new HashMap<>();

    private List<String> lastBadSyntax = new ArrayList<>();

    private String message;
    private String[] args;

    @SuppressWarnings("unchecked")
    public CommandContext(String messasge, String[] args, Command command) {
        this.arguments = Arrays.asList(command.arguments());
        this.message = message;
        this.args = args;

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
        List<CommandArg<?>> list = arguments.stream().filter(arg -> arg.getLabel().equalsIgnoreCase(label)).collect(Collectors.toList());
        if (list.size() > 0)
            return list.get(0);
        return null;
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
        for (CommandArg<?> arg : arguments)
            if (!hasArg(arg.getLabel()))
                lastBadSyntax.add(String.format("Missing required argument: %s, with type %s", arg.getLabel(), arg.getType()));

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
