package me.zero.client.api.exception;

/**
 * Exception thrown whenever the outcome of an action is unexpected.
 * Essentially, the process cannot proceed because of the outcome.
 * Suspends the Java Virtual Machine.
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public class UnexpectedOutcomeException extends RuntimeException {

    public UnexpectedOutcomeException(String message) {
        super(message);
    }
}
