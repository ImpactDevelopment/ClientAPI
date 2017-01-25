package me.zero.client.api.util;

import me.zero.client.api.util.logger.Level;

/**
 * Storage for all Messages used by the Logger's logf method.
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

    String EVENT_UNABLE_CALL                   = "Unable to Call Event %s";
}
