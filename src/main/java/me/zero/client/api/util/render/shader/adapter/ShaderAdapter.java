package me.zero.client.api.util.render.shader.adapter;

import me.zero.client.api.util.render.shader.glenum.GlShaderStatus;
import me.zero.client.api.util.render.shader.glenum.GlShaderType;

/**
 * The shell for a Shader Adapter. Used as
 * a universal accessor for different types
 * of shaders (Specifically ARB and 2.0)
 *
 * @author Brady
 * @since 5/21/2017 11:52 AM
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
    int createShader(GlShaderType type);

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
    void attachObject(int program, int shader);

    /**
     * Detaches a shader object from a shader program
     *
     * @param program The shader program id
     * @param shader The shader object id
     */
    void detachObject(int program, int shader);

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
     * the value is {@code GL_FALSE} or not, if it is,
     * then a {@code ShaderException} should be thrown.
     *
     * @param program The shader program id
     * @param status The status flag being checked
     */
    void checkStatus(int program, GlShaderStatus status);

    /**
     * Gets a shader program's log info
     *
     * @param program The shader progrma id
     * @return The log info
     */
    String getProgramLogInfo(int program);
}
