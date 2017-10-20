package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;

/**
 * @author Brady
 * @since 10/20/2017 11:04 AM
 */
public final class CharParser implements ArgumentParser<Character> {

    @Override
    public final Character parse(ExecutionContext context, Class<?> type, String raw) {
        if (raw.length() == 1) {
            return raw.charAt(0);
        }
        return null;
    }

    @Override
    public final boolean isTarget(Class<?> type) {
        return Character.class.isAssignableFrom(type) || Character.TYPE.isAssignableFrom(type);
    }
}
