package me.zero.client.load;

/**
 * Called when a state occurs in which the program
 * is unable to proceed while the Client is being
 * initialized by the LaunchClassLoader.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/27/2017 12:00PM
 */
public final class ClientInitException extends RuntimeException {

    public ClientInitException(String message) {
        super(message);
    }
}
