package me.zero.client.api.command.parse;

import me.zero.client.api.command.parse.defaults.NumberParser;
import me.zero.client.api.command.parse.defaults.PlayerParser;
import net.minecraft.client.network.NetworkPlayerInfo;

/**
 * Class for making CommandArgs.
 *
 * @author Brady
 * @since 2/16/2017 12:00 PM
 */
@Deprecated
public final class GenericArgs {

    private GenericArgs() {}

    /**
     * Creates a String argument
     *
     * @param label The argument label
     * @return The created argument
     */
    public static CommandArg<String> string(String label) {
        return create(label, String.class, s -> s);
    }

    /**
     * Creates a player argument, defined by NetworkPlayerInfo
     *
     * @param label The argument label
     * @return The created argument
     */
    public static CommandArg<NetworkPlayerInfo> player(String label) {
        return create(label, NetworkPlayerInfo.class, new PlayerParser());
    }

    /**
     * Creates a number argument, defaults to a double
     *
     * @param label The argument label
     * @return The created argument
     */
    public static CommandArg<Double> number(String label) {
        return create(label, Double.class, new NumberParser());
    }

    /**
     * Creates a CommandArg
     *
     * @param label Argument label
     * @param tClass Argument type
     * @param parser Argument parser
     * @return The created CommandArg
     */
    private static <T> CommandArg<T> create(String label, Class<T> tClass, ArgumentParser<T> parser) {
        return new CommandArg<>(label, tClass, parser);
    }
}
