# Example
The example client is provided to show the usage of ClientAPI and is included in the
CDK (Client Development Kit). For those who would like to start from stratch, below is
an overview of the usage of the basic features of ClientAPI.

## Creating the Client JSON
The JSON is loaded using [Gson](https://github.com/google/gson), mapping the JSON file
to an instance of ``clientapi.ClientInfo``. An example of a properly defined ``client.json``
file is below. Please note that comments are not official supported in JSON, and if you choose
to copy the config below, they must be removed.

```js
{
  "name": "Example Client",           // The name of the Client
  "authors": [ "Your Name" ],         // An array of the Client authors
  "id": "example",                    // A unique ID for the Client
  "build": 1.0,                       // The build/version number
  "type": "RELEASE",                  // The version type, either ALPHA, BETA, SNAPSHOT, or RELEASE
  "main": "com.example.ExampleClient" // The classpath of the main class extending clientapi.Client
}

```

The JSON file should be named ``client.json`` (Case Sensitive) and be located in the root
directory of the compiled client jar file. When using the CDK, it should be located in
``src/main/java/resources`` to be recognized when compiling in a testing environment.

## Creating the Client class
All Clients/Utility mods extend ``clientapi.Client`` and must inherit the ``onInit(ClientInfo)``
method and the default ``(ClientInfo)`` constructor. Even though there should only be one active
instance of the ``Client`` class per game instance, ClientAPI does not provide a static method to
access the instance of the Client, therefore it is recommended that one is added.

```java
public final class ExampleClient extends Client {
    
    private static ExampleClient instance;
    
    public ExampleClient(ClientInfo info) {
        super(info);
        instance = this;
    }
    
    @Override
    public final void onInit(ClientInfo info) {
        // Your init code here
    }
    
    public static ExampleClient getInstance() {
        return instance;
    }
}
```

## Modules
Modules provide an easy way to have [toggleable](https://github.com/ImpactDevelopment/ClientAPI/blob/master/src/main/java/clientapi/util/interfaces/Toggleable.java)
listeners contained within a class. For more information on how to contain listeners, view the [Alpine repository](https://github.com/ZeroMemes/Alpine/).
All Modules extend ``clientapi.module.Module`` and are required to have a ``@Mod()`` annotation
(``clientapi.module.Mod``) *or* an implementation of one of the multi-parameter constructors. In
both cases, the bind parameter is optional. An example of both are below.
```java
@Mod(name = "Example Module", description = "An example module", bind = Keyboard.KEY_R)
public final class ExampleModule extends Module {
    
}
```
```java
public final class ExampleModule extends Module {
    
    public ExampleModule() {
        super("Example Module", "An example module", Keyboard.KEY_R);
    }    
}
```

## The Manager
ClientAPI provides a class called ``Manager``, packaged under ``clientapi.manage.Manager``. This
class is used to organize the instances of a class, defined by the generic type. ``Manager<T>``
extends ``ArrayList<T>``, allowing full access and manipulation of the contents. Manager also
provides a method which returns entries in the Manager based on class. (``get(Class)``).
```java
public final class ManagerExample extends Manager<String> {
    
    public ManagerExample(String name) {
        super(name);
        
        String entry = "Test";
        
        // Add an entry to this manager
        this.add(entry);
        
        // Remove the entry from this manager
        this.add(entry);
        
        // Clear all entries from this manager
        this.clear();
        
        // Remove duplicate entries from this manager
        Set<String> nonDuplicateSet = new LinkedHashSet<>(this);
        this.clear();
        this.addAll(nonDuplicateSet);
    }
}
```

## Creating the Module Manager
Create a new class that extends ``Manager<Module>`` and implement the default methods. In order
to register a new module, an instance of it must be created and added in the ``load()`` method
of the Module Manager.
```java
public final class ExampleModManager extends Manager<Module> {
    
    public ExampleModManager() {
        super("Module");
    }
    
    @Override
    public final void load() {
        this.addAll(
            new SomeModule()
        );
    }
    
    @Override
    public final void save() {
        
    }
}
```
In your main class, create an instance of the new Module Manager class and call the ``load()``
method.
```java
public final class ExampleClient extends Client {
    
    private static ExampleClient instance;
    private ExampleModManager moduleManager;
    
    public ExampleClient(ClientInfo info) {
        super(info);
        instance = this;
    }
    
    @Override
    public final void onInit(ClientInfo info) {
        moduleManager = new ExampleModManager();
        moduleManager.load();
    }
    
    public final ExampleModManager getModuleManager() {
        return this.moduleManager;
    }
    
    public static ExampleClient getInstance() {
        return instance;
    }
}
```

## Creating an Event Listener
Event listeners are provided by [Alpine](https://github.com/ZeroMemes/Alpine) and allow for any
class with valid listener fields to receive events when they are posted to the ClientAPI event
manager. (``clientapi.ClientAPI#EVENT_BUS``). I will create a basic flight mod to demonstrate.
```java
@Mod(name = "Fly", description = "Allows you to fly", bind = Keyboard.KEY_F)
public final class Fly extends Module implements IMovement {
    
    @EventHandler
    private Listener<UpdateEvent> updateListener = new Listener<>(event ->
        mc.player.motionY = Boolean.compare(mc.gameSettings.keyBindJump.isKeyDown(), mc.gameSettings.keyBindSneak.isKeyDown()) * 0.2
    );
}
```
This sets the local player's Y-axis motion to the comparator result of the jump key bind state and
the sneak key bind state multiplied by 0.2.

If the jump key bind state is **true**, and the sneak
key bind state is **false**, ``compare(boolean, boolean)`` will return **1**, If the jump key bind
state is **false**, and the sneak key bind state is **true**, ``compare(boolean, boolean)`` will
return **-1**, and if both of the key states are **true** or both are **false**,
``compare(boolean, boolean)`` will return **0**.

There are many more events than just ``UpdateEvent``, all of which are packaged under
``clientapi.event``.

## Commands
Commands are a way of executing some task from in-game. For example, commands may be used to set
the keybinding for a module, list all of the modules, execute a script, etc. Commands can have 
arguments that are used to instruct the task that is being executed by the command.

All Commands extend ``clientapi.command.Command`` and are required to have a ``@Cmd()`` annotation
(``clientapi.command.Cmd``) *or* an implementation of one of the multi-parameter constructors.
In both cases, the bind parameter is optional. An example of both are below.
```java
@Cmd(headers = { "example", "ex" }, description = "An example command")
public final class ExampleCommand extends Command {
    
}
```
```java
public final class ExampleCommand extends Command {
    
    public ExampleCommand() {
        super(new String[] { "example", "ex", }, "An example command");
    }    
}
```
To create a new command handle, create a new method with a single ``ExecutionContext`` parameter
(``clientapi.command.execute.ExecutionContext``) and the ``@Sub`` annotation (``clientapi.command.Sub``).
``Sub`` is used to define the properties of the sub command, which are execution headers, argument
names, and a description.

The headers parameter is used to identify the handle from the first execution argument. If no
headers are defined by a handle, it is assumed that the first given argument for execution
directly correlates with the first argument of the method, excluding the Execution Context.

The arguments paremeter is used to give a name to each argument parameter. It is not a requirement
if the environment that it is running in does not have [obfuscated](https://en.wikipedia.org/wiki/Obfuscation_(software))
method parameters.

``java.util.Optional`` can be used to define optional parameters. As of the current revision, there
can only be a single optional parameter per handle, and it must be the last parameter.

Here is an example of a command with a single default handle...
```java
@Cmd(headers = { "example", "ex" }, description = "An example command")
public final class ExampleCommand extends Command {
    
    @Sub(arguments = { "arg1", "arg2" }, description = "An example command handle")
    private void execute(ExecutionContext context, String required, Optional<String> optional) {
        // Print out the executor of the command, and both arguments. If the
        // optional parameter contains no value, interpret it as "undefined".
        mc.ingameGUI.getChatGUI().printChatMessage(
                new ChatBuilder()
                        .append(String.format("%s executed the ", context.sender().getName()), TextFormatting.GRAY)
                        .append("\"Test\"", TextFormatting.WHITE)
                        .append(" command with argument(s) ", TextFormatting.GRAY)
                        .append(required, TextFormatting.WHITE)
                        .append(" and ", TextFormatting.GRAY)
                        .append(optional.orElse("undefined"), TextFormatting.WHITE)
                        .build());
    }
}
```
The syntax would be as follows
```
<arg1> [arg2]
```
A command with multiple handles may look like...
```java
@Cmd(headers = { "example", "ex" }, description = "An example command")
public final class ExampleCommand extends Command {
    
    @Sub(headers = { "add" }, arguments = { "element" }, description = "Adds an element")
    private void add(ExecutionContext context, String elementName) {
        // Add the element
    }
    
    @Sub(headers = { "remove" }, arguments = { "element" }, description = "Removes an element")
    private void remove(ExecutionContext context, String elementName) {
        // Remove the element
    }
}
```
The syntax would be as follows
```
add <element>
remove <element>
```

## Creating the Command Manager
Create a new class that extends ``Manager<Command>`` and implement the default methods. In order
to register a new command, an instance of it must be created and added in the ``load()`` method
of the Module Manager.

An instance of ``clientapi.command.CommandHandler`` is required for the execution of commands, it
is responsible for parsing command arguments and carrying out execution. In order for the
command handler to understand how to parse certain parameter types, they must be specified.

ClientAPI provides a handful of parsers that cover all primitive types and additional ones (Such as
``java.util.Optional``) that are used for features in the command system.
```java
public final class ExampleCommandManager extends Manager<Command> {
    
    private final CommandHandler handler = new CommandHandler(this);
    
    public ExampleCommandManager() {
        super("Command");
    }
    
    @Override
    public final void load() {
        // Register all of the default parsers
        handler.registerParser(new BlockParser());
        handler.registerParser(new BooleanParser());
        handler.registerParser(new CharParser());
        handler.registerParser(new CommandParser(ExampleClient.getInstance().getCommandManager()));
        handler.registerParser(new ModuleParser(ExampleClient.getInstance().getModuleManager()));
        handler.registerParser(new NumberParser());
        handler.registerParser(new OptionalParser());
        handler.registerParser(new StringParser());
        
        // Subscribe the handler and a chat command listener to the event bus
        ClientAPI.EVENT_BUS.subscribe(
                handler,
                new ChatCommandListener(handler)
        );
    }
    
    @Override
    public final void save() {
        
    }
}
```
In your main class, create an instance of the new Command Manager class and call the ``load()``
method.
```java
public final class ExampleClient extends Client {
    
    private static ExampleClient instance;
    private Manager<Module> moduleManager;
    private Manager<Command> commandManager;
    
    public ExampleClient(ClientInfo info) {
        super(info);
        instance = this;
    }
    
    @Override
    public final void onInit(ClientInfo info) {
        moduleManager = new ExampleModManager();
        moduleManager.load();
        
        commandManager = new ExampleCommandManager();
        commandManager.load();
    }
    
    public final Manager<Module> getModuleManager() {
        return this.moduleManager;
    }
    
    public final Manager<Command> getCommandManager() {
        return this.commandManager;
    }
    
    public static ExampleClient getInstance() {
        return instance;
    }
}
```

## Creating a Command Argument Parser
For parsing parameter types that aren't covered by the ClientAPI default parsers, or you want to
effectively replace one of the ClientAPI parsers, it is very simple to do so.

All argument parsers implement ``clientapi.command.executor.argument.ArgumentParser``. There are 2
methods that must be implemented, ``parse(ExecutionContext, Type, String)`` and ``isTarget(Type)``.

The ``parse`` method is invoked only if the ``isTarget`` method returns true for a specified type.

Let's create a parser for hex colors as an example.
```java
public final class ColorParser implements ArgumentParser<Color> {
    
    @Override
    public final Color parse(ExecutionContext context, Type type, String raw) {
        // Remove all non-hexadecimal characters
        raw = raw.replaceAll("([^0-9a-fA-F])", "");
        
        // If there aren't either 24 or 32 bits, return null
        if (raw.length() != 6 && raw.length() != 8) {
            return null;
        }
        
        // If there are only 24 bits, set the first 8 (alpha) to equal 255.
        if (raw.length() == 6) {
            raw = "FF" + raw;
        }
        
        return new Color(Integer.parseInt(raw, 16), true);
    }
  
    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && Color.class.isAssignableFrom((Class) type);
    }
}
```