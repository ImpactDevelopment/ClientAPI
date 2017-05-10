package me.zero.client.api.exception;

/**
 * Thrown when an action is being carried out,
 * that either should be or is not supported.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/25/2017 12:00 PM
 */
public final class ActionNotSupportedException extends RuntimeException {

    public ActionNotSupportedException(String message) {
        super(message);
    }
}
