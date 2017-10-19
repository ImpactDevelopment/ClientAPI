package clientapi.command.executor.argument;

/**
 * @author Brady
 * @since 10/18/2017 11:10 AM
 */
public final class BooleanParser implements ArgumentParser<Boolean> {

    @Override
    public final Boolean parse(String raw) {
        switch (raw.toLowerCase()) {
            case "true":
            case "yes":
            case "1":
                return true;
            case "false":
            case "no":
            case "0":
                return false;
            default:
                return null;
        }
    }

    @Override
    public final boolean isTarget(Class<?> type) {
        return Boolean.class.isAssignableFrom(type) || Boolean.TYPE.isAssignableFrom(type);
    }
}
