package me.zero.client.api.command.parse.defaults;

import me.zero.client.api.command.parse.ArgumentParser;

/**
 * Gets a Number repesented as a Double
 * from a string.
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public class NumberParser implements ArgumentParser<Double> {

    @Override
    public Double parse(String num) {
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
