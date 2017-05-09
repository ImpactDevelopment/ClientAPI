package me.zero.client.api.util;

import me.zero.client.api.util.logger.Level;

/**
 * Storage for all Messages used by the Logger's logf method
 * as well as some generic messages sent to the client from
 * the api.
 *
 * @see me.zero.client.api.util.logger.ILogger#logf(Level, String, Object...)
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public interface Messages {

    String TRANSFORM                           = "Transforming %s using %s";
    String TRANSFORM_CLASS_NOT_FOUND           = "Unable to Transform, Class not found!, %s";
    String TRANSFORM_METHOD_NOT_FOUND          = "Unable to Transform, Method not found!";
    String TRANSFORM_CANNOT_COMPILE            = "Unable to Compile Class! %s";
    String TRANSFORM_UNEXPECTED_IOEXCEPTION    = "Unexpected IOException after Compiling Class %s";
    String TRANSFORM_INSTANTIATION             = "Unable to instantiate Transformer, %s";
    String TRANSFORM_WRAPPER_COMPILE_METHOD    = "Unable to compile Method, %s";
    String TRANSFORM_WRAPPER_ADD_METHOD        = "Unable to add Method to Class, %s";

    String REFERENCE_CLASS_CTCLASS             = "Unable to get CtClass representation of %s";

    String PLUGIN_LOAD                         = "Loaded plugin from %s";
    String PLUGIN_JARFILE_CREATE               = "Unable to Create plugin JarFile, %s";
    String PLUGIN_INSTANTIATION                = "Unable to instantiate PluginMain, %s";
    String PLUGIN_CANT_CREATE_MODULE           = "Unable to create Module, %s";
    String PLUGIN_CANT_LOAD_CLASS              = "Unable to load Class, %s";
    String PLUGIN_CANT_CREATE_INPUTSTREAM      = "Unable to create jar InputStream, %s";

    String CLIENT_INSTANTIATION                = "Unable to instantiate Client, %s";
    String CLIENT_NOT_FOUND                    = "Unable to find Client Class, %s";

    String MODULE_INSTANTIATION                = "Unable to instantiate Module, %s";

    String COMMAND_MISSING_ARGS                = "Missing required argument: %s, with type %s";
    String COMMAND_INVALID                     = "Invalid Command";
}
