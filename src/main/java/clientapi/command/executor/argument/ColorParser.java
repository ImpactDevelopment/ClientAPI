package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;

import java.awt.*;
import java.lang.reflect.Type;

/**
 * @author Brady
 * @since 2/8/2018 5:25 PM
 */
public final class ColorParser implements ArgumentParser<Color> {

    @Override
    public final Color parse(ExecutionContext context, Type type, String raw) {
        // Remove all non-hexadecimal characters
        raw = raw.replaceAll("([^0-9a-fA-F])", "");

        // If there aren't either 24 or 32 bits, return null
        if (raw.length() != 6 && raw.length() != 8) {
            return null;
        }

        // If there are only 24 bits, set the first 8 (alpha) to equal 255.
        if (raw.length() == 6) {
            raw = "FF" + raw;
        }

        return new Color(Integer.parseInt(raw, 16), true);
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && Color.class.isAssignableFrom((Class) type);
    }
}
