package me.zero.client.api.command.parse.defaults;

import me.zero.client.api.command.parse.ArgumentParser;

/**
 * Parses a number (Represented by a Double)
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public final class NumberParser implements ArgumentParser<Double> {

    @Override
    public Double apply(String t) {
        try {
            return Double.parseDouble(t);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
