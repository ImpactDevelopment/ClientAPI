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
public class UniformVariable {

    private String name;
    private int location;

    private UniformVariable(String name, int location) {
        this.name = name;
        this.location = location;
    }

    public void setInt(int value) {
        glUniform1iARB(location, value);
    }

    public void setFloat(float value) {
        glUniform1fARB(location, value);
    }

    public void setBoolean(boolean value) {
        glUniform1fARB(location, value ? 1 : 0);
    }

    public void setVec(Vec2 value) {
        glUniform2fARB(location, value.getX(), value.getY());
    }

    public void setVec(Vec3 value) {
        glUniform3fARB(location, value.getX(), value.getY(), value.getZ());
    }

    public String getName() {
        return this.name;
    }

    public int getLocation() {
        return this.location;
    }

    public static UniformVariable get(int shaderID, String uniformName) {
        return new UniformVariable(uniformName, glGetUniformLocationARB(shaderID, uniformName));
    }
}
