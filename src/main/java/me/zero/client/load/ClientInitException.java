package me.zero.client.load;

/**
 * Called when a state occurs in which the program
 * is unable to proceed while the Client is being
 * initialized by the LaunchClassLoader.
 *
 * @since 1.0
 *
 * Created by Brady on 4/27/2017.
 */
public class ClientInitException extends RuntimeException {

    public ClientInitException(String message) {
        super(message);
    }
}
