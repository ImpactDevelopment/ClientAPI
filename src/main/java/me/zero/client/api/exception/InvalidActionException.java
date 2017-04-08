package me.zero.client.api.exception;

/**
 * Exception thrown whenever an action is called that should not be called.
 * Suspends the Java Virtual Machine.
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public final class InvalidActionException extends RuntimeException {

    public InvalidActionException(String message) {
        super(message);
    }
}
