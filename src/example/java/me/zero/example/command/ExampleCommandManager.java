package me.zero.example.command;

import clientapi.ClientAPI;
import clientapi.command.Command;
import clientapi.command.handler.CommandHandler;
import clientapi.command.handler.listener.ChatCommandListener;
import clientapi.manage.Manager;
import me.zero.example.command.commands.TestCommand;

/**
 * Created by Brady on 2/16/2017.
 */
public final class ExampleCommandManager extends Manager<Command> {

    private final CommandHandler handler = new CommandHandler(this);

    public ExampleCommandManager() {
        super("Command");
    }

    @Override
    public void load() {
        this.addData(
                new TestCommand()
        );

        // Setup the handler and a chat command listener
        ClientAPI.EVENT_BUS.subscribe(
                handler,
                new ChatCommandListener(handler)
        );
    }

    @Override
    public void save() {}
}
