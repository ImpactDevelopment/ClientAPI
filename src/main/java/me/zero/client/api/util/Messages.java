package me.zero.client.api.util;

import me.zero.client.api.util.logger.Level;

/**
 * Storage for all Messages used by the Logger's logf method
 * as well as some generic messages sent to the client from
 * the api.
 *
 * @see me.zero.client.api.util.logger.ILogger#logf(Level, String, Object...)
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
public interface Messages {

    String PLUGIN_LOAD                         = "Loaded plugin from %s";
    String PLUGIN_JARFILE_CREATE               = "Unable to Create plugin JarFile, %s";
    String PLUGIN_INSTANTIATION                = "Unable to instantiate PluginMain, %s";
    String PLUGIN_CANT_CREATE_MODULE           = "Unable to create Module, %s";
    String PLUGIN_CANT_LOAD_CLASS              = "Unable to load Class, %s";
    String PLUGIN_CANT_CREATE_INPUTSTREAM      = "Unable to create jar InputStream, %s";

    String MODULE_INSTANTIATION                = "Unable to instantiate Module, %s";

    String COMMAND_MISSING_ARGS                = "Missing required argument: %s, with type %s";
    String COMMAND_INVALID                     = "Invalid Command";
}
