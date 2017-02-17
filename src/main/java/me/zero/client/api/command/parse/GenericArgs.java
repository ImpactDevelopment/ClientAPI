package me.zero.client.api.command.parse;

import me.zero.client.api.command.parse.defaults.NumberParser;
import me.zero.client.api.command.parse.defaults.PlayerParser;
import net.minecraft.client.network.NetworkPlayerInfo;

/**
 * Class for making CommandArgs.
 *
 * @since 1.0
 *
 * Created by Brady on 2/16/2017.
 */
public class GenericArgs {

    public static CommandArg<String> string(String label) {
        return create(label, String.class, s -> s);
    }

    public static CommandArg<NetworkPlayerInfo> player(String label) {
        return create(label, NetworkPlayerInfo.class, new PlayerParser());
    }

    public static CommandArg<Double> number(String label) {
        return create(label, Double.class, new NumberParser());
    }

    private static <T> CommandArg<T> create(String label, Class<T> tClass, ArgumentParser<T> parser) {
        return new CommandArg<>(label, tClass, parser, false);
    }
}
