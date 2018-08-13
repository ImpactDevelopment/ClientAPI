package clientapi.command.executor.parser;

import clientapi.command.ChildCommand;
import clientapi.command.Command;
import clientapi.util.ClientAPIUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Used to parse raw text into the individual arguments that will be used for execution
 *
 * @author Brady
 * @since 4/14/2018
 */
public enum CommandInputParser {

    INSTANCE;

    private static final Pattern REGEX = Pattern.compile("\"([^\"]+)\"|'([^']+)'|([^\\s]+)");

    /**
     * Parses raw text command execution input
     *
     * @param raw The raw input
     * @return An optional containing the parsed input if the input is valid
     */
    public final Optional<ParsedCommandInput> parseCommandInput(String raw) {
        // Create a matcher to parse the message
        Matcher matcher = REGEX.matcher(raw);

        // Collect all of the matches from the raw data
        List<String> matches = new ArrayList<>();
        while (matcher.find())
            matches.add(matcher.group());

        // If there were no matches, then the input was invalid
        if (matches.size() == 0)
            return Optional.empty();

        String[] args = new String[matches.size() - 1];
        for (int i = 1; i < matches.size(); i++)
            args[i - 1] = matches.get(i).replace("\"", "").replace("\'", "");

        return Optional.of(new ParsedCommandInput(matches.get(0), args));
    }

    /**
     * Finds a child command that matches
     *
     * @param command The command
     * @param arguments The arguments
     * @param strictArgs Whether or not non-header children have argument length checked
     * @return The child command if one that matches the arguments is found
     */
    public final Optional<ChildCommand> findChild(Command command, String[] arguments, boolean strictArgs) {
        List<ChildCommand> children = findPossibleChildren(command, arguments, strictArgs);
        switch (children.size()) {
            case 0: // There were no matches
                return Optional.empty();
            case 1: // There was a match by child command header
                return Optional.of(children.get(0));
            default: // There was a match based on argument length if strictArgs is true
                return strictArgs
                        ? Optional.of(children.get(0))
                        // If argument count wasn't checked, find the closest argument length
                        : children.stream().min(Comparator.comparingInt(c -> Math.abs(c.getHeaders().length - arguments.length)));
        }
    }

    /**
     * Returns the possible target children given the input args
     *
     * @param command The command
     * @param arguments The arguments
     * @param strictArgs Whether or not non-header children have argument length checked
     * @return The possible children
     */
    public final List<ChildCommand> findPossibleChildren(Command command, String[] arguments, boolean strictArgs) {
        String header = arguments.length == 0 ? null : arguments[0];

        // Find the command by the header defined by the first argument
        ChildCommand child = command.getChildren().stream()
                .filter(c -> header == null ? c.getHeaders().length == 0 : ClientAPIUtils.containsIgnoreCase(c.getHeaders(), header))
                .findFirst().orElse(null);

        if (child != null)
            return Collections.singletonList(child);

        // Find the command by the length of the arguments
        return command.getChildren().stream()
                .filter(c -> c.getHeaders().length == 0 && (!strictArgs || arguments.length == c.getArguments().size()))
                .collect(Collectors.toList());
    }
}
