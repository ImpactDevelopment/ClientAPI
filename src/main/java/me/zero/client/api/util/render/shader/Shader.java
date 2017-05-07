package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.interfaces.Helper;

import java.util.ArrayList;
import java.util.List;

import static me.zero.client.api.util.render.shader.ShaderHelper.*;
import static org.lwjgl.opengl.ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.GL_VERTEX_SHADER_ARB;

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
    private final List<Uniform> uniforms = new ArrayList<>();

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
        Uniform v = uniforms.stream().filter(uniform -> uniform.getName().equals(name)).findFirst().orElse(null);
        if (v == null)
            return loadUniform(name);
        return v;
    }

    /**
     * Loads a Uniform Variable from the specified name
     *
     * @since 1.0
     *
     * @param name The Uniform Name
     * @return The Uniform Variable
     */
    private Uniform loadUniform(String name) {
        Uniform v = Uniform.get(programID, name);
        this.uniforms.add(v);
        return v;
    }
}
