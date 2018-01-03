package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Brady
 * @since 10/20/2017 11:09 AM
 */
public final class NumberParser implements ArgumentParser<Number> {

    @Override
    public final Number parse(ExecutionContext context, Type type, String raw) {
        if (NumberUtils.isCreatable(raw)) {
            return NumberUtils.createNumber(raw);
        }
        return null;
    }

    @Override
    public final boolean isTarget(Type type) {
        if (!(type instanceof Class)) {
            return false;
        }

        Class c = (Class) type;
        // Check all NumberUtils#createNumber(String) supported types
        // Integer -> BigInteger
        // Float -> BigDecimal
        return Integer.class.isAssignableFrom(c)
                || Long.class.isAssignableFrom(c)
                || BigInteger.class.isAssignableFrom(c)
                || Float.class.isAssignableFrom(c)
                || Double.class.isAssignableFrom(c)
                || BigDecimal.class.isAssignableFrom(c)
                || Integer.TYPE.isAssignableFrom(c)
                || Long.TYPE.isAssignableFrom(c)
                || Float.TYPE.isAssignableFrom(c)
                || Double.TYPE.isAssignableFrom(c);
    }
}
