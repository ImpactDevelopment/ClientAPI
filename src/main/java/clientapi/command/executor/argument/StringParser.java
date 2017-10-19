package clientapi.command.executor.argument;

/**
 * @author Brady
 * @since 10/18/2017 11:14 AM
 */
public final class StringParser implements ArgumentParser<String> {

    @Override
    public final String parse(String raw) {
        return raw;
    }

    @Override
    public final boolean isTarget(Class<?> type) {
        return String.class.isAssignableFrom(type);
    }
}
