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
public class ShaderHelper {

    public static void checkObjecti(int objID, int name) {
        if (glGetObjectParameteriARB(objID, name) == 0)
            throw new UnexpectedOutcomeException(getLogInfo(objID));
    }

    public static String getLogInfo(int objID) {
        return glGetInfoLogARB(objID, glGetObjectParameteriARB(objID, GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
}
