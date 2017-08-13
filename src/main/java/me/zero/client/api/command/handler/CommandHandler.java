/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.command.handler;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.client.api.command.Command;
import me.zero.client.api.command.exception.CommandException;
import me.zero.client.api.command.exception.UnknownCommandException;
import me.zero.client.api.command.exception.handler.ExceptionHandler;
import me.zero.client.api.command.executor.CommandExecutor;
import me.zero.client.api.command.executor.DirectExecutor;
import me.zero.client.api.event.defaults.game.core.KeyEvent;
import me.zero.client.api.event.defaults.internal.CommandExecutionEvent;
import me.zero.client.api.manage.Manager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles and processes command execution events
 *
 * @author Brady
 * @since 6/1/2017 3:03 PM
 */
public final class CommandHandler {

    /**
     * Handlers to process command exceptions
     */
    private final List<ExceptionHandler> handlers = new ArrayList<>();

    /**
     * Executor that is called to execute commands. The default executor
     * is set to a {@code DirectExecutor}
     */
    private CommandExecutor executor = new DirectExecutor();

    /**
     * Command manager using this handler
     */
    private final Manager<Command> commandManager;

    /**
     * Prefix used to indicate command input. The default
     * prefix is .
     */
    private char prefix = '.';

    public CommandHandler(Manager<Command> commandManager) {
        this.commandManager = commandManager;
    }

    @EventHandler
    private final Listener<CommandExecutionEvent> commandExecutionListener = new Listener<>(event -> {
        try {
            if (event.getCommand() != null)
                executor.execute(event.getCommand(), event.getSender(), event.getArguments());
            else
                throw new UnknownCommandException();
        } catch (CommandException e) {
            List<ExceptionHandler> handlers = findHandlers(e);
            if (handlers.isEmpty())
                e.printStackTrace();
            else
                handlers.forEach(handler -> handler.accept(e));
        }
    });

    /**
     * Handle key events
     *
     * Opens chat if the prefix is typed
     *
     * @since 2.2
     */
    @EventHandler
    private final Listener<KeyEvent> keyEventListener = new Listener<>(event -> {
        if (Keyboard.getEventCharacter() == this.getPrefix()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiChat(String.valueOf(this.getPrefix())));
        }
    });

    /**
     * Finds handlers that target the specified CommandException
     *
     * @param exception The command exception
     * @return The list of handlers, empty if none
     */
    private List<ExceptionHandler> findHandlers(CommandException exception) {
        return handlers.stream().filter(handler -> handler.getType() == exception.getClass()).collect(Collectors.toList());
    }

    /**
     * Registers an {@code ExceptionHandler} to the handlers list.
     */
    public final void registerHandler(ExceptionHandler handler) {
        if (!handlers.contains(handler))
            handlers.add(handler);
    }

    /**
     * @return The client that is using this handler
     */
    public final Manager<Command> getCommandManager() {
        return this.commandManager;
    }

    /**
     * Sets the command executor
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
     * @param prefix
     *
     * @since 2.2
     */
    public final void setPrefix(char prefix) {
        this.prefix = prefix;
    }

    /**
     * Set the chat command prefix by using the first char of a string
     *
     * @param prefix
     *
     * @deprecated 2.2
     */
    @Deprecated
    public final void setPrefix(String prefix) {
        this.prefix = prefix.charAt(0);
    }

    /**
     * @return The chat command prefix as a char
     *
     * @since 2.2
     */
    public final char getPrefix() {
        return prefix;
    }
}
