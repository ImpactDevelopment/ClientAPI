package me.zero.client.api.exception;

/**
 * Called when an array size is not sufficient
 * or exceeds a certain amount.
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
 */
public final class ArraySizeException extends RuntimeException {

    public ArraySizeException(String message) {
        super(message);
    }
}
