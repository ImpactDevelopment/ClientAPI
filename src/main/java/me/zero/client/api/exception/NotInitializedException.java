package me.zero.client.api.exception;

/**
 * Called when a field is accessed, but the field
 * has not yet been initialized and has a null value.
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
 */
public final class NotInitializedException extends RuntimeException {

    public NotInitializedException(String message) {
        super(message);
    }
}
