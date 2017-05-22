package me.zero.client.api.exception;

/**
 * Called during value registry when an error is run into.
 *
 * @author Brady
 * @since 5/22/2017 5:25 PM
 */
public final class ValueException extends RuntimeException {

    public ValueException(String message) {
        super(message);
    }
}
