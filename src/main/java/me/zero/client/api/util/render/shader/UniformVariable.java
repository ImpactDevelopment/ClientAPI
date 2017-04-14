package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;

import static org.lwjgl.opengl.ARBShaderObjects.*;

/**
 * A representation of a GLSL Uniform Variable
 *
 * @since 1.0
 *
 * Created by Brady on 2/16/2017.
 */
public final class UniformVariable {

    /**
     * The Uniform name
     */
    private String name;

    /**
     * The Uniform Object ID
     */
    private int location;

    private UniformVariable(String name, int location) {
        this.name = name;
        this.location = location;
    }

    /**
     * Sets the value of this Uniform as an Int
     *
     * @since 1.0
     *
     * @param value New value
     */
    public void setInt(int value) {
        glUniform1iARB(location, value);
    }

    /**
     * Sets the value of this Uniform as a Float
     *
     * @since 1.0
     *
     * @param value New value
     */
    public void setFloat(float value) {
        glUniform1fARB(location, value);
    }

    /**
     * Sets the value of this Uniform as a Boolean
     *
     * @since 1.0
     *
     * @param value New value
     */
    public void setBoolean(boolean value) {
        glUniform1fARB(location, value ? 1 : 0);
    }

    /**
     * Sets the value of this Uniform as a Vec2
     *
     * @since 1.0
     *
     * @param value New value
     */
    public void setVec(Vec2 value) {
        glUniform2fARB(location, value.getX(), value.getY());
    }

    /**
     * Sets the value of this Uniform as a Vec3
     *
     * @since 1.0
     *
     * @param value New value
     */
    public void setVec(Vec3 value) {
        glUniform3fARB(location, (float) value.getX(), (float) value.getY(), (float) value.getZ());
    }

    /**
     * @since 1.0
     *
     * @return The name of this UniformVariable
     */
    public String getName() {
        return this.name;
    }

    /**
     * @since 1.0
     *
     * @return The Object ID of this UniformVariable
     */
    public int getLocation() {
        return this.location;
    }

    /**
     * Creates a uniform variable from the shader object id and the uniform's name
     *
     * @since 1.0
     *
     * @param shaderID Shader object ID
     * @param uniformName Uniform Name
     * @return The UniformVariable representation
     */
    public static UniformVariable get(int shaderID, String uniformName) {
        return new UniformVariable(uniformName, glGetUniformLocationARB(shaderID, uniformName));
    }
}
