/*
 * Copyright 2017 ZeroMemes
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

package clientapi.api.util.render.gl.shader.adapter;

import clientapi.api.exception.ShaderException;
import clientapi.api.util.render.gl.glenum.GLShaderType;
import clientapi.api.util.render.gl.glenum.GLShaderStatus;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

/**
 * @author Brady
 * @since 5/21/2017 12:00 PM
 */
final class GL20ShaderAdapter implements ShaderAdapter {

    GL20ShaderAdapter() {}

    @Override
    public int createProgram() {
        return glCreateProgram();
    }

    @Override
    public void linkProgram(int programObj) {
        glLinkProgram(programObj);
    }

    @Override
    public void validateProgram(int programObj) {
        glValidateProgram(programObj);
    }

    @Override
    public int createShader(GLShaderType type) {
        Objects.requireNonNull(type);

        switch (type) {
            case VERTEX:
                return glCreateShader(GL_VERTEX_SHADER);
            case FRAGMENT:
                return glCreateShader(GL_FRAGMENT_SHADER);
            case GEOMETRY:
                return glCreateShader(GL_GEOMETRY_SHADER);
        }

        return 0;
    }

    @Override
    public void shaderSource(int shader, CharSequence source) {
        glShaderSource(shader, source);
    }

    @Override
    public void compileShader(int shader) {
        glCompileShader(shader);
    }

    @Override
    public void attachShader(int program, int shader) {
        glAttachShader(program, shader);
    }

    @Override
    public void detachShader(int program, int shader) {
        glDetachShader(program, shader);
    }

    @Override
    public void deleteProgram(int program) {
        glDeleteProgram(program);
    }

    @Override
    public void deleteShader(int shader) {
        glDeleteShader(shader);
    }

    @Override
    public void useProgram(int program) {
        glUseProgram(program);
    }

    @Override
    public void checkStatus(int program, GLShaderStatus status) {
        Objects.requireNonNull(status);

        int pname = 0;
        switch (status) {
            case COMPILE:
                pname = GL_COMPILE_STATUS;
                break;
            case LINK:
                pname = GL_LINK_STATUS;
                break;
            case VALIDATE:
                pname = GL_VALIDATE_STATUS;
                break;
        }

        if (glGetProgrami(program, pname) == GL_FALSE)
            throw new ShaderException(getProgramLogInfo(program));
    }

    @Override
    public String getProgramLogInfo(int program) {
        return glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH));
    }
}
