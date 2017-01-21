package me.zero.client.api.util.logger;

/**
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public interface ILogger {

    void log(Level level, String message);

    void logf(Level level, String message, Object... args);
}
