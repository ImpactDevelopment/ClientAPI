package me.zero.client.api.exception;

/**
 * Exception thrown whenever an action is called that should not be called.
 * Suspends the Java Virtual Machine.
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public class ActionNotValidException extends RuntimeException {

    public ActionNotValidException(String message) {
        super(message);
    }
}
