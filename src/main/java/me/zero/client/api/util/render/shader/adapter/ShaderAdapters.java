package me.zero.client.api.util.render.shader.adapter;

import org.lwjgl.opengl.GLContext;

/**
 * Used to retrieve the system shader adapter instance
 *
 * @author Brady
 * @since 5/21/2017 12:27 PM
 */
public final class ShaderAdapters {

    /**
     * System supported shader adapter
     */
    private static final ShaderAdapter systemDefault;

    private ShaderAdapters() {}

    static {
        // Check if OpenGL 2.0 is supported by the machine
        if (GLContext.getCapabilities().OpenGL20)
            systemDefault = new GL20ShaderAdapter();
        else
            systemDefault = new ARBShaderAdapter();
    }

    /**
     * Returns the discovered system compatible shader adapter.
     * The adapter is determined by checking OpenGL version
     * compatibility.
     *
     * @return System supported shader adapter
     */
    public static ShaderAdapter getSystemAdapter() {
        return systemDefault;
    }
}
