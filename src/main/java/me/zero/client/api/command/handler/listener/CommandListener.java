package me.zero.client.api.command.handler.listener;

import me.zero.client.api.command.handler.CommandHandler;

/**
 * @author Brady
 * @since 6/11/2017 1:47 PM
 */
public class CommandListener {

    protected final CommandHandler handler;

    public CommandListener(CommandHandler handler) {
        this.handler = handler;
    }
}
