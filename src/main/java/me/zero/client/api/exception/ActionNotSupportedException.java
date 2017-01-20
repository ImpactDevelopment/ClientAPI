package me.zero.client.api.exception;

/**
 * Exception thrown whenever an action is called that should not be called.
 * Suspends the Java Virtual Machine
 *
 * Created by Brady on 1/19/2017.
 */
public class ActionNotSupportedException extends RuntimeException {

    public ActionNotSupportedException(String message) {
        super(message);
    }
}
