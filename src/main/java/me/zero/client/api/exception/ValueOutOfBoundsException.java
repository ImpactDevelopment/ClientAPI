package me.zero.client.api.exception;

/**
 * Thrown when a parameterized value exceeds or does
 * not meet the required value.
 *
 * @author Brady
 * @since 5/10/2017 4:20 PM
 */
public class ValueOutOfBoundsException extends RuntimeException {

    public ValueOutOfBoundsException(String message) {
        super(message);
    }
}
