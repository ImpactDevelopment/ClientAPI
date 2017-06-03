package me.zero.client.api.command.exception.handler;

import me.zero.client.api.command.exception.CommandException;

import java.util.function.Consumer;

/**
 * Put in place by developers to hook into when exceptions
 * are thrown. Implementations should have an {@code ExceptionTarget}
 * annotation placed at the type declaration so that the command
 * handler may identify the target exception type. The main purpose
 * of implementing handlers is to provide users with custom messages
 * when an exception is thrown, rather than a default console output
 * performed by the Client API.
 *
 * @see ExceptionTarget
 *
 * @author Brady
 * @since 6/1/2017 2:54 PM
 */
@FunctionalInterface
public interface ExceptionHandler extends Consumer<CommandException> {

    /**
     * Retrieves the command exception type from the
     * {@code ExceptionTarget} that belongs to this
     * type.
     *
     * @return The command exception type
     */
    default Class<? extends CommandException> getType() {
        return this.getClass().getAnnotation(ExceptionTarget.class).value();
    }
}
