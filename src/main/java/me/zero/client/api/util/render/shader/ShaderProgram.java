package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.io.StreamReader;
import me.zero.client.api.util.render.EXTFramebuffer;
import net.minecraft.client.shader.Framebuffer;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.GL_VERTEX_SHADER_ARB;
import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

/**
 * Used to create ARB Shader Programs with OpenGL
 *
 * @since 1.0
 *
 * Created by Brady on 2/16/2017.
 */
public abstract class ShaderProgram implements Helper {

    /**
     * The uniform variables for this shader
     */
    private final List<UniformVariable> uniforms = new ArrayList<>();

    /**
     * Various Object IDs
     */
    private final int programID, fragmentID, vertexID;

    /**
     * EXTFramebuffer used to render shader
     */
    private final EXTFramebuffer framebuffer;

    public ShaderProgram(String vertex, String fragment, Framebuffer fbo) {
        framebuffer = new EXTFramebuffer(fbo);
        programID = glCreateProgramObjectARB();
        vertexID = loadShader(vertex, GL_VERTEX_SHADER_ARB);
        fragmentID = loadShader(fragment, GL_FRAGMENT_SHADER_ARB);

        glAttachObjectARB(programID, vertexID);
        glAttachObjectARB(programID, fragmentID);

        loadProgram();
    }

    /**
     * Updates this framebuffer with the values
     * from the specified framebuffer texture
     *
     * @since 1.0
     *
     * @param textureID Framebuffer texture
     */
    final void update(int textureID) {
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebuffer.getFramebufferID());
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);

        glUseProgramObjectARB(programID);

        glActiveTexture(GL_TEXTURE0);
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);

        this.update();

        glPushMatrix();
        glColor4f(1, 1, 1, 1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_SRC_COLOR);
        glBegin(GL_POLYGON);
        glTexCoord2d(0, 1);
        glVertex2d(0, 0);
        glTexCoord2d(0, 0);
        glVertex2d(0, framebuffer.getFramebufferHeight() / 2);
        glTexCoord2d(1, 0);
        glVertex2d(framebuffer.getFramebufferWidth() / 2, framebuffer.getFramebufferHeight() / 2);
        glTexCoord2d(1, 0);
        glVertex2d(framebuffer.getFramebufferWidth() / 2, framebuffer.getFramebufferHeight() / 2);
        glTexCoord2d(1, 1);
        glVertex2d(framebuffer.getFramebufferWidth() / 2, 0);
        glTexCoord2d(0, 1);
        glVertex2d(0, 0);
        glEnd();
        glDisable(GL_BLEND);
        glPopMatrix();

        glUseProgramObjectARB(0);
    }

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
        framebuffer.delete();
    }

    /**
     * Called to update Uniform Variables
     *
     * @since 1.0
     */
    public abstract void update();

    /**
     * Loads a Uniform Variable from the specified name
     *
     * @since 1.0
     *
     * @param name The Uniform Name
     * @return The Uniform Variable
     */
    private UniformVariable loadUniform(String name) {
        UniformVariable v = UniformVariable.get(programID, name);
        this.uniforms.add(v);
        return v;
    }

    /**
     * Gets a uniform variable from the specified name
     *
     * @since 1.0
     *
     * @param name The Uniform Name
     * @return The Uniform Variable
     */
    protected final UniformVariable getUniform(String name) {
        UniformVariable v = uniforms.stream().filter(uniform -> uniform.getName().equals(name)).findFirst().orElse(null);
        if (v == null)
            return loadUniform(name);
        return v;
    }

    /**
     * Loads a shader of the specified type from the specified path
     *
     * @since 1.0
     *
     * @param path Shader path
     * @param type Shader type
     * @return The Shader's Object ID
     */
    private int loadShader(String path, int type) {
        String src = new StreamReader(ShaderProgram.class.getResourceAsStream(path)).read();
        int shaderID = glCreateShaderObjectARB(type);
        glShaderSourceARB(shaderID, src);
        glCompileShaderARB(shaderID);
        ShaderHelper.checkObjecti(shaderID, GL_OBJECT_COMPILE_STATUS_ARB);
        return shaderID;
    }

    /**
     * Loads this ShaderProgram
     *
     * @since 1.0
     */
    private void loadProgram() {
        glLinkProgramARB(programID);
        ShaderHelper.checkObjecti(programID, GL_OBJECT_LINK_STATUS_ARB);
        glValidateProgramARB(programID);
        ShaderHelper.checkObjecti(programID, GL_OBJECT_VALIDATE_STATUS_ARB);
        glUseProgramObjectARB(0);
    }

    /**
     * @since 1.0
     *
     * @return The Shader's Framebuffer
     */
    final EXTFramebuffer getFramebuffer() {
        return this.framebuffer;
    }
}
