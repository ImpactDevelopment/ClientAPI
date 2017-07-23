/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.util.render.gl.shader.adapter;

import me.zero.client.api.exception.ShaderException;
import me.zero.client.api.util.render.gl.glenum.GLShaderStatus;
import me.zero.client.api.util.render.gl.glenum.GLShaderType;

import java.util.Objects;

import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBGeometryShader4.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexShader.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author Brady
 * @since 5/21/2017 12:07 PM
 */
final class ARBShaderAdapter implements ShaderAdapter {

    ARBShaderAdapter() {}

    @Override
    public int createProgram() {
        return glCreateProgramObjectARB();
    }

    @Override
    public void linkProgram(int programObj) {
        glLinkProgramARB(programObj);
    }

    @Override
    public void validateProgram(int programObj) {
        glValidateProgramARB(programObj);
    }

    @Override
    public int createShader(GLShaderType type) {
        Objects.requireNonNull(type);

        switch (type) {
            case VERTEX:
                return glCreateShaderObjectARB(GL_VERTEX_SHADER_ARB);
            case FRAGMENT:
                return glCreateShaderObjectARB(GL_FRAGMENT_SHADER_ARB);
            case GEOMETRY:
                return glCreateShaderObjectARB(GL_GEOMETRY_SHADER_ARB);
        }

        return 0;
    }

    @Override
    public void shaderSource(int shader, CharSequence source) {
        glShaderSourceARB(shader, source);
    }

    @Override
    public void compileShader(int shader) {
        glCompileShaderARB(shader);
    }

    @Override
    public void attachObject(int program, int shader) {
        glAttachObjectARB(program, shader);
    }

    @Override
    public void detachObject(int program, int shader) {
        glDetachObjectARB(program, shader);
    }

    @Override
    public void useProgram(int program) {
        glUseProgramObjectARB(program);
    }

    @Override
    public void deleteProgram(int program) {
        glDeleteObjectARB(program);
    }

    @Override
    public void deleteShader(int shader) {
        glDeleteObjectARB(shader);
    }

    @Override
    public void checkStatus(int program, GLShaderStatus status) {
        Objects.requireNonNull(status);

        int pname = 0;
        switch (status) {
            case COMPILE:
                pname = GL_OBJECT_COMPILE_STATUS_ARB;
                break;
            case LINK:
                pname = GL_OBJECT_LINK_STATUS_ARB;
                break;
            case VALIDATE:
                pname = GL_OBJECT_VALIDATE_STATUS_ARB;
                break;
        }

        if (glGetObjectParameteriARB(program, pname) == GL_FALSE)
            throw new ShaderException(getProgramLogInfo(program));
    }

    @Override
    public String getProgramLogInfo(int program) {
        return glGetInfoLogARB(program, glGetObjectParameteriARB(program, GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
}
