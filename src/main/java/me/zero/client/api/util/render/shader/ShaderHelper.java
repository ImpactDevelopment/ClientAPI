package me.zero.client.api.util.render.shader;

import me.zero.client.api.exception.UnexpectedOutcomeException;

import static org.lwjgl.opengl.ARBShaderObjects.*;

/**
 * Some utils for ARB Shaders
 *
 * @since 1.0
 *
 * Created by Brady on 2/16/2017.
 */
final class ShaderHelper {

    private ShaderHelper() {}

    /**
     * Checks an arb object parameter
     *
     * @since 1.0
     *
     * @param objID The object's ID
     * @param name The name of the object
     */
    static void checkObjecti(int objID, int name) {
        if (glGetObjectParameteriARB(objID, name) == 0)
            throw new UnexpectedOutcomeException(getLogInfo(objID));
    }

    /**
     * Gets the error that an object produced
     *
     * @since 1.0
     *
     * @param objID The object's ID
     * @return The error
     */
    private static String getLogInfo(int objID) {
        return glGetInfoLogARB(objID, glGetObjectParameteriARB(objID, GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
}
