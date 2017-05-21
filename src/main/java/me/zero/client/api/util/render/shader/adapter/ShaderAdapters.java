package me.zero.client.api.util.render.shader.adapter;

import org.lwjgl.opengl.GLContext;

/**
 * @author Brady
 * @since 5/21/2017 12:27 PM
 */
public final class ShaderAdapters {

    private static final ShaderAdapter systemDefault;

    private ShaderAdapters() {}

    static {
        if (GLContext.getCapabilities().OpenGL20)
            systemDefault = new GL20ShaderAdapter();
        else
            systemDefault = new ARBShaderAdapter();
    }

    public static ShaderAdapter getSystemDefault() {
        return systemDefault;
    }
}
