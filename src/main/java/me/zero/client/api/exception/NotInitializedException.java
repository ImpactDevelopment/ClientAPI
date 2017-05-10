package me.zero.client.api.exception;

/**
 * Called when a field is accessed, but the field
 * has not yet been initialized and has a null value.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class NotInitializedException extends RuntimeException {

    public NotInitializedException(String message) {
        super(message);
    }
}
