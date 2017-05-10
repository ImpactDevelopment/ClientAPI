package me.zero.client.api.util.logger;

/**
 * Implementation of ILogger
 *
 * @see me.zero.client.api.util.logger.ILogger
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
public final class Logger implements ILogger {

    private Logger() {}

    /**
     * Instance of the Logger
     */
    public static final Logger instance = new Logger();

    /**
     * Implementation of {@code #log(Level, String)}
     *
     * @see me.zero.client.api.util.logger.ILogger#log(Level, String)
     *
     * @param level The level
     * @param message The message
     */
    @Override
    public final void log(Level level, String message) {
        System.out.printf("[ClientAPI][%s] %s \n", level.toString(), message);
    }

    /**
     * Implementation of {@code #logf(Level, String, Object...)}
     *
     * @see me.zero.client.api.util.logger.ILogger#logf(Level, String, Object...)
     *
     * @param level The level
     * @param message The message
     * @param args The format arguments
     */
    @Override
    public final void logf(Level level, String message, Object... args) {
        this.log(level, String.format(message, args));
    }
}
