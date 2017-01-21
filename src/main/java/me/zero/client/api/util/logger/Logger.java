package me.zero.client.api.util.logger;

/**
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public class Logger implements ILogger {

    public static final Logger instance = new Logger();

    @Override
    public void log(Level level, String message) {
        System.out.printf("[ClientAPI][%s] %s \n", level.toString(), message);
    }

    @Override
    public void logf(Level level, String message, Object... args) {
        this.log(level, String.format(message, args));
    }
}
