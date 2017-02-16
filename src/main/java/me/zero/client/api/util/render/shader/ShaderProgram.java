package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.interfaces.Action;
import me.zero.client.api.util.io.StreamReader;

import java.util.ArrayList;
import java.util.List;

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
public abstract class ShaderProgram implements Action {

    private List<UniformVariable> uniforms = new ArrayList<>();
    private int programID, fragmentID, vertexID;

    public ShaderProgram(String vertex, String fragment) {
        programID = glCreateProgramObjectARB();
        vertexID = loadShader(vertex, GL_VERTEX_SHADER_ARB);
        fragmentID = loadShader(fragment, GL_FRAGMENT_SHADER_ARB);

        attachShader(vertexID);
        attachShader(fragmentID);

        loadProgram();
        loadUniforms();
    }

    @Override
    public final void start() {
        glUseProgramObjectARB(programID);
    }

    @Override
    public final void stop() {
        glUseProgramObjectARB(0);
    }

    protected abstract void loadUniforms();

    protected final void loadUniform(String name) {
        this.uniforms.add(UniformVariable.get(programID, name));
    }

    public final void cleanUp() {
        stop();
        glDetachObjectARB(programID, vertexID);
        glDetachObjectARB(programID, fragmentID);
        glDeleteObjectARB(vertexID);
        glDeleteObjectARB(fragmentID);
        glDeleteObjectARB(programID);
    }

    private int loadShader(String path, int type) {
        String src = new StreamReader(ShaderProgram.class.getResourceAsStream(path)).read();
        int shaderID = glCreateShaderObjectARB(type);
        glShaderSourceARB(shaderID, src);
        glCompileShaderARB(shaderID);
        ShaderHelper.checkObjecti(shaderID, GL_OBJECT_COMPILE_STATUS_ARB);
        return shaderID;
    }

    private void attachShader(int shaderID) {
        glAttachObjectARB(programID, shaderID);
    }

    private void loadProgram() {
        glLinkProgramARB(programID);
        ShaderHelper.checkObjecti(programID, GL_OBJECT_LINK_STATUS_ARB);
        glValidateProgramARB(programID);
        ShaderHelper.checkObjecti(programID, GL_OBJECT_VALIDATE_STATUS_ARB);
        stop();
    }
}
