package me.zero.client.api.util.logger;

/**
 * Implementation of ILogger
 *
 * @see me.zero.client.api.util.logger.ILogger
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public class Logger implements ILogger {

    /**
     * Instance of the Logger
     */
    public static final Logger instance = new Logger();

    /**
     * Implementation of {@code #log(Level, String)}
     *
     * @since 1.0
     *
     * @see me.zero.client.api.util.logger.ILogger#log(Level, String)
     *
     * @param level The level
     * @param message The message
     */
    @Override
    public void log(Level level, String message) {
        System.out.printf("[ClientAPI][%s] %s \n", level.toString(), message);
    }

    /**
     * Implementation of {@code #logf(Level, String, Object...)}
     *
     * @since 1.0
     *
     * @see me.zero.client.api.util.logger.ILogger#logf(Level, String, Object...)
     *
     * @param level The level
     * @param message The message
     * @param args The format arguments
     */
    @Override
    public void logf(Level level, String message, Object... args) {
        this.log(level, String.format(message, args));
    }
}
