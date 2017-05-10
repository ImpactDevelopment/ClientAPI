package me.zero.client.api.exception;

/**
 * Exception thrown whenever an action is called that should not be called.
 * Suspends the Java Virtual Machine.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public final class InvalidActionException extends RuntimeException {

    public InvalidActionException(String message) {
        super(message);
    }
}
