/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.command.handler;

import clientapi.ClientAPI;
import clientapi.command.Cmd;
import clientapi.command.Command;
import clientapi.command.exception.CommandException;
import clientapi.command.exception.UnknownCommandException;
import clientapi.command.exception.handler.ExceptionHandler;
import clientapi.command.executor.CommandExecutor;
import clientapi.command.executor.parser.ArgumentParser;
import clientapi.event.defaults.game.core.KeyEvent;
import clientapi.event.defaults.internal.CommandExecutionEvent;
import clientapi.manage.Manager;
import clientapi.util.interfaces.Helper;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.GuiChat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles and processes command execution events.
 *
 * @see Command
 * @see Cmd
 *
 * @author Brady
 * @since 6/1/2017 3:03 PM
 */
public final class CommandHandler implements Helper {

    /**
     * Handlers to process command exceptions
     */
    private final List<ExceptionHandler> handlers = new ArrayList<>();

    /**
     * Argument parsers to resolve command argument types
     */
    private final List<ArgumentParser<?>> parsers = new ArrayList<>();

    /**
     * Executor that is called to execute commands. All commands
     * are passed through the executor. Default is set to
     * {@code CommandExecutor#DIRECT}
     *
     * @see CommandExecutor#DIRECT
     */
    private CommandExecutor executor = CommandExecutor.DIRECT;

    /**
     * Command manager using this handler. Used to access
     * the list of commands that the client provides.
     */
    private final Manager<Command> commandManager;

    /**
     * Prefix used to indicate command input. By default, it is set to "."
     */
    private String prefix = ".";

    /**
     * Whether or not typing the prefix char should open the in-game chat gui
     */
    private boolean openChat = false;

    public CommandHandler(Manager<Command> commandManager) {
        this.commandManager = commandManager;
        ClientAPI.EVENT_BUS.subscribe(this);
    }

    @EventHandler
    private final Listener<KeyEvent> keyEventListener = new Listener<>(event -> {
        // Check if opening chat with the prefix is supported
        if (!openChat || prefix == null || mc.ingameGUI == null)
            return;

        // Ensure the prefix is a single character
        if (prefix.length() == 1 && prefix.charAt(0) == event.getCharacter())
            mc.displayGuiScreen(new GuiChat(prefix));
    });

    @EventHandler
    private final Listener<CommandExecutionEvent> commandExecutionListener = new Listener<>(event -> {
        try {
            if (event.getCommand() != null) {
                executor.execute(event.getCommand(), event.getContext(), event.getArguments());
                return;
            }
            throw new UnknownCommandException();
        } catch (CommandException e) {
            List<ExceptionHandler> handlers = getExceptionHandlers(e);
            if (handlers.isEmpty()) {
                e.printStackTrace();
                return;
            }

            handlers.forEach(handler -> handler.handle(e));
        }
    });

    /**
     * Finds handlers that target the specified {@code CommandException}
     *
     * @param exception The command exception
     * @return The list of handlers, empty if none
     */
    private List<ExceptionHandler> getExceptionHandlers(CommandException exception) {
        return handlers.stream().filter(handler -> handler.isTarget(exception.getClass())).collect(Collectors.toList());
    }

    /**
     * Registers an {@code ExceptionHandler} to the handlers list.
     *
     * @see ExceptionHandler
     *
     * @param handler {@code ExceptionHandler} being registered.
     */
    public final void registerHandler(ExceptionHandler handler) {
        if (!handlers.contains(handler))
            handlers.add(handler);
    }

    /**
     * Finds a parser that targets the specified {@code Class} type.
     *
     * @param type The type
     * @return A parser targeting the type, {@code null} if none
     */
    public final ArgumentParser getParser(Type type) {
        return parsers.stream().filter(parser -> parser.isTarget(type)).findFirst().orElse(null);
    }

    /**
     * Registers an {@code ArgumentParser} to the parsers list.
     *
     * @see ArgumentParser
     * @see CommandHandler#getParser(Type)
     *
     * @param parser {@code ArgumentParser} being registered
     */
    public final void registerParser(ArgumentParser<?> parser) {
        if (!parsers.contains(parser))
            parsers.add(parser);
    }

    /**
     * @return The client that is using this handler
     */
    public final Manager<Command> getCommandManager() {
        return this.commandManager;
    }

    /**
     * Sets the command executor
     *
     * @param executor New command executor
     */
    public final void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    /**
     * @return The command executor in use
     */
    public final CommandExecutor getExecutor() {
        return this.executor;
    }

    /**
     * Sets the chat command prefix
     *
     * @param prefix New chat command prefix
     */
    public final void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return The chat command prefix
     */
    public final String getPrefix() {
        return this.prefix;
    }

    /**
     * Sets whether or not typing the prefix char should open the in-game chat gui
     *
     * @param openChat The new state
     */
    public final void setPrefixOpenChat(boolean openChat) {
        this.openChat = openChat;
    }

    /**
     * @return Whether or not typing the prefix char should open the in-game chat gui
     */
    public final boolean doesPrefixOpenChat() {
        return this.openChat;
    }
}
