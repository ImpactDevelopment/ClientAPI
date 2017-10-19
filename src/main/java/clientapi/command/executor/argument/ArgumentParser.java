package clientapi.command.executor.argument;

/**
 * @author Brady
 * @since 10/18/2017 11:05 AM
 */
public interface ArgumentParser<T> {

    /**
     * Returns the resolved/parsed type from a
     * raw string representation.
     *
     * @param raw Type represented in a string
     * @return String resolved to type
     */
    T parse(String raw);

    /**
     * @param type A type
     * @return Whether or not the specified type is a target for this parser.
     */
    boolean isTarget(Class<?> type);
}
