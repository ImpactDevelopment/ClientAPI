package me.zero.example.command;

import me.zero.client.api.command.Command;
import me.zero.client.api.command.parse.CommandArg;
import me.zero.client.api.command.parse.CommandContext;
import me.zero.client.api.command.parse.GenericArgs;
import me.zero.client.api.manage.Manager;
import me.zero.example.ExampleClient;

/**
 * Created by Brady on 2/16/2017.
 */
public class CommandManager extends Manager<Command> {

    public CommandManager() {
        super("Command");
    }

    @Override
    public void load() {
        // Para, if you see this please hit the back button in your browser
        // kthanks
        // anonymous classes XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
        this.addData(new Command() {

            @Override
            public String[] label() {
                return new String[] { "test" };
            }

            @Override
            public String description() {
                return "A test command";
            }

            @Override
            public CommandArg[] arguments() {
                return new CommandArg[] {
                        GenericArgs.string("text")
                };
            }

            @Override
            public void execute(CommandContext context) {
                ExampleClient client = ExampleClient.getInstance();
                client.printChatMessage("Test Command!");
                client.printChatMessage((String) context.get("text"));
            }
        });
    }

    @Override
    public void save() {}
}
