package me.zero.client.api.exception;

/**
 * Called when an array size is not sufficient
 * or exceeds a certain amount.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class ArraySizeException extends RuntimeException {

    public ArraySizeException(String message) {
        super(message);
    }
}
