package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;

import java.lang.reflect.Type;

/**
 * @author Brady
 * @since 10/20/2017 11:04 AM
 */
public final class CharParser implements ArgumentParser<Character> {

    @Override
    public final Character parse(ExecutionContext context, Type type, String raw) {
        if (raw.length() == 1) {
            return raw.charAt(0);
        }
        return null;
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && (Character.class.isAssignableFrom((Class) type) || Character.TYPE.isAssignableFrom((Class) type));
    }
}
