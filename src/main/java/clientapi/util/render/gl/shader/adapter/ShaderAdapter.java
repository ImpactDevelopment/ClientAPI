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

package clientapi.util.render.gl.shader.adapter;

import clientapi.util.render.gl.glenum.GLShaderStatus;
import clientapi.util.render.gl.glenum.GLShaderType;
import clientapi.util.render.gl.shader.exception.ShaderException;
import org.lwjgl.opengl.GL11;

/**
 * The shell for a Shader Adapter. Used as
 * a universal accessor for different types
 * of shaders (Specifically ARB and 2.0)
 *
 * @author Brady
 * @since 5/21/2017
 */
public interface ShaderAdapter {

    /**
     * Creates a new shader program. The location in
     * memory of the shader program is returned if it
     * was successfully created, if not, 0 is returned.
     *
     * @return Shader location if success, 0 if not.
     */
    int createProgram();

    /**
     * Links the shader program so that it may be used.
     *
     * @param programObj Shader program object id
     */
    void linkProgram(int programObj);

    /**
     * Validates the shader program
     *
     * @param programObj Shader program object id
     */
    void validateProgram(int programObj);

    /**
     * Creates a shader to be used with a shader program
     *
     * @param type The type of shader
     * @return The shader's object id
     */
    int createShader(GLShaderType type);

    /**
     * Attaches the shader's source with the shader object.
     *
     * @param shader The shader object id
     * @param source The source of the shader
     */
    void shaderSource(int shader, CharSequence source);

    /**
     * Compiles a shader object after it was created
     * and the source was attached
     *
     * @param shader The shader object id
     */
    void compileShader(int shader);

    /**
     * Attaches a shader object to a shader program
     *
     * @param program The shader program id
     * @param shader The shader object id
     */
    void attachShader(int program, int shader);

    /**
     * Detaches a shader object from a shader program
     *
     * @param program The shader program id
     * @param shader The shader object id
     */
    void detachShader(int program, int shader);

    /**
     * Prepares the specified shader program for usage
     *
     * @param program The shader program id
     */
    void useProgram(int program);

    /**
     * Deletes a shader program from memory
     *
     * @param program The program object id
     */
    void deleteProgram(int program);

    /**
     * Deletes a shader object from memory
     *
     * @param shader The shader object id
     */
    void deleteShader(int shader);

    /**
     * Checks a program's variable status to see if
     * the value is {@link GL11#GL_FALSE} or not, if it is,
     * then a {@link ShaderException} should be thrown.
     *
     * @param program The shader program id
     * @param status The status flag being checked
     */
    void checkStatus(int program, GLShaderStatus status);

    /**
     * Gets a shader program's log info
     *
     * @param program The shader program id
     * @return The log info
     */
    String getProgramLogInfo(int program);

    /**
     * Sets the integer value of the specified uniform variable
     *
     * @param location The uniform variable location
     * @param value The integer value
     */
    void setUniform(int location, int value);

    /**
     * Sets the float value of the specified uniform variable
     *
     * @param location The uniform variable location
     * @param value The float value
     */
    void setUniform(int location, float value);

    /**
     * Sets the 2 float values of the specified uniform variable
     *
     * @param location The uniform variable location
     * @param value1 The first float value
     * @param value2 The second float value
     */
    void setUniform(int location, float value1, float value2);

    /**
     * Sets the 3 float values of the specified uniform variable
     *
     * @param location The uniform variable location
     * @param value1 The first float value
     * @param value2 The second float value
     * @param value3 The third float value
     */
    void setUniform(int location, float value1, float value2, float value3);

    /**
     * Retrieves the location of the specified uniform variable name
     * from the specified shader program.
     *
     * @param program The shader program id
     * @param name The uniform variable name
     * @return The uniform variable location
     */
    int getUniformLocation(int program, String name);
}
