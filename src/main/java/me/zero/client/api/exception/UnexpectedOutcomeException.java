package me.zero.client.api.exception;

/**
 * Exception thrown whenever the outcome of an action is unexpected.
 * Essentially, the process cannot proceed because of the outcome.
 * Suspends the Java Virtual Machine.
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
public final class UnexpectedOutcomeException extends RuntimeException {

    public UnexpectedOutcomeException(String message) {
        super(message);
    }
}
