package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.interfaces.Helper;

import java.util.HashMap;
import java.util.Map;

import static me.zero.client.api.util.render.shader.ShaderHelper.*;
import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.*;

/**
 * Used to create ARB Shader Programs with OpenGL
 *
 * @since 1.0
 *
 * Created by Brady on 2/16/2017.
 */
public abstract class Shader implements Helper {

    /**
     * The uniform variables for this shader
     */
    private final Map<String, Uniform> uniforms = new HashMap<>();

    /**
     * Various Object IDs
     */
    private final int programID, fragmentID, vertexID;

    public Shader(String vertex, String fragment) {
        programID = glCreateProgramObjectARB();
        vertexID = loadShader(vertex, GL_VERTEX_SHADER_ARB);
        fragmentID = loadShader(fragment, GL_FRAGMENT_SHADER_ARB);

        glAttachObjectARB(programID, vertexID);
        glAttachObjectARB(programID, fragmentID);

        createProgram(programID);
    }

    /**
     * Attaches the shader program
     *
     * @since 1.0
     */
    public final void attach() {
        glUseProgramObjectARB(programID);
        update();
    }

    /**
     * Detaches the shader program
     *
     * @since 1.0
     */
    public final void detach() {
        glUseProgramObjectARB(0);
    }

    /**
     * Called after the shader program is
     * attached to update the uniform vars
     *
     * @since 1.0
     */
    public abstract void update();

    /**
     * Deletes this ShaderProgram
     *
     * @since 1.0
     */
    public final void delete() {
        glUseProgramObjectARB(0);
        glDetachObjectARB(programID, vertexID);
        glDetachObjectARB(programID, fragmentID);
        glDeleteObjectARB(vertexID);
        glDeleteObjectARB(fragmentID);
        glDeleteObjectARB(programID);
    }

    /**
     * Gets a uniform variable from the specified name
     *
     * @since 1.0
     *
     * @param name The Uniform Name
     * @return The Uniform Variable
     */
    protected final Uniform getUniform(String name) {
        return uniforms.computeIfAbsent(name, n -> Uniform.get(programID, n));
    }
}
