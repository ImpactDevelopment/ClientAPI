package clientapi.command.executor;

import clientapi.command.handler.CommandHandler;
import clientapi.command.sender.CommandSender;

/**
 * Provides commands context to the cause of execution. Context
 * is defined by the {@code CommandSender} and {@code CommandHandler}.
 *
 * @author Brady
 * @since 10/18/2017 11:19 AM
 */
public interface ExecutionContext {

    /**
     * @return The {@code CommandSender} responsible for the command execution
     */
    CommandSender sender();

    /**
     * @return The {@code CommandHandler} that carried out command execution
     */
    CommandHandler handler();

    static ExecutionContext of(CommandSender sender, CommandHandler handler) {
        return new Impl(sender, handler);
    }

    /**
     * Implementation of {@code ExecutionContext}, used when
     * creating an instance of {@code ExecutionContext}
     * from {@code ExecutionContext#of(CommandSender, CommandHandler)}
     *
     * @see ExecutionContext#of(CommandSender, CommandHandler)
     */
    class Impl implements ExecutionContext {

        /**
         * {@code CommandSender} responsible for command execution
         */
        private final CommandSender sender;

        /**
         * {@code CommandHandler} that carried out command execution
         */
        private final CommandHandler handler;

        private Impl(CommandSender sender, CommandHandler handler) {
            this.sender = sender;
            this.handler = handler;
        }

        @Override
        public CommandSender sender() {
            return this.sender;
        }

        @Override
        public CommandHandler handler() {
            return this.handler;
        }
    }
}
