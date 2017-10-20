package clientapi.command.executor.argument;

import clientapi.command.executor.ExecutionContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Brady
 * @since 10/20/2017 11:09 AM
 */
public final class NumberParser implements ArgumentParser<Number> {

    @Override
    public final Number parse(ExecutionContext context, Class<?> type, String raw) {
        if (NumberUtils.isCreatable(raw)) {
            return NumberUtils.createNumber(raw);
        }
        return null;
    }

    @Override
    public final boolean isTarget(Class<?> type) {
        // Check all NumberUtils#createNumber(String) supported types
        // Integer -> BigInteger
        // Float -> BigDecimal
        return Integer.class.isAssignableFrom(type)
                || Long.class.isAssignableFrom(type)
                || BigInteger.class.isAssignableFrom(type)
                || Float.class.isAssignableFrom(type)
                || Double.class.isAssignableFrom(type)
                || BigDecimal.class.isAssignableFrom(type)
                || Integer.TYPE.isAssignableFrom(type)
                || Long.TYPE.isAssignableFrom(type)
                || Float.TYPE.isAssignableFrom(type)
                || Double.TYPE.isAssignableFrom(type);
    }
}
