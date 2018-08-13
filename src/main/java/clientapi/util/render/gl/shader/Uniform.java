/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.util.render.gl.shader;

import clientapi.util.math.Vec2;
import clientapi.util.math.Vec3;
import clientapi.util.render.gl.shader.adapter.ShaderAdapter;
import clientapi.util.render.gl.shader.adapter.ShaderAdapters;

/**
 * A representation of a GLSL Uniform Variable
 *
 * @author Brady
 * @since 2/16/2017
 */
public final class Uniform {

    /**
     * Instance of the system supported shader adapter
     */
    private static final ShaderAdapter adapter = ShaderAdapters.getSystemAdapter();

    /**
     * The Uniform name
     */
    private final String name;

    /**
     * The Uniform Object ID
     */
    private final int location;

    private Uniform(String name, int location) {
        this.name = name;
        this.location = location;
    }

    /**
     * Sets the value of this Uniform as an Int
     *
     * @param value New value
     */
    public final void setInt(int value) {
        adapter.setUniform(location, value);
    }

    /**
     * Sets the value of this Uniform as a Float
     *
     * @param value New value
     */
    public final void setFloat(float value) {
        adapter.setUniform(location, value);
    }

    /**
     * Sets the value of this Uniform as a Boolean
     *
     * @param value New value
     */
    public final void setBoolean(boolean value) {
        adapter.setUniform(location, value ? 1 : 0);
    }

    /**
     * Sets the value of this Uniform as a Vec2
     *
     * @param value New value
     */
    public final void setVec(Vec2 value) {
        adapter.setUniform(location, value.getX(), value.getY());
    }

    /**
     * Sets the value of this Uniform as a Vec3
     *
     * @param value New value
     */
    public final void setVec(Vec3 value) {
        adapter.setUniform(location, (float) value.getX(), (float) value.getY(), (float) value.getZ());
    }

    /**
     * @return The name of this UniformVariable
     */
    public final String getName() {
        return this.name;
    }

    /**
     * @return The Object ID of this UniformVariable
     */
    public final int getLocation() {
        return this.location;
    }

    /**
     * Creates a uniform variable from the shader object ID and the uniform's name
     *
     * @param programID Shader program ID
     * @param uniformName Uniform Name
     * @return The UniformVariable representation
     */
    public static Uniform get(int programID, String uniformName) {
        return new Uniform(uniformName, adapter.getUniformLocation(programID, uniformName));
    }
}
