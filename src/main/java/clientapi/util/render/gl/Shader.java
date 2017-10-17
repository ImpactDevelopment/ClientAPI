/*
 * Copyright 2017 ImpactDevelopment
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

package clientapi.util.render.gl;

import clientapi.exception.ShaderException;
import clientapi.util.render.gl.glenum.GLShaderType;
import clientapi.util.render.gl.shader.adapter.ShaderAdapters;
import clientapi.util.render.gl.glenum.GLShaderStatus;
import clientapi.util.render.gl.shader.adapter.ShaderAdapter;

/**
 * @author Brady
 * @since 7/23/2017 3:24 PM
 */
public final class Shader extends GLObject {

    /**
     * Instance of the system supported shader adapter
     */
    private static final ShaderAdapter adapter = ShaderAdapters.getSystemAdapter();

    /**
     * Source code of the shader
     */
    private final String src;

    /**
     * Type of shader
     */
    private final GLShaderType type;

    public Shader(GLShaderType type, String src) {
        this.type = type;
        this.src = src;
    }

    @Override
    protected int nativeGen() {
        int shaderID = adapter.createShader(type);
        if (shaderID == 0)
            return UNABLE_TO_GENERATE;

        try {
            adapter.shaderSource(shaderID, src);
            adapter.compileShader(shaderID);
            adapter.checkStatus(shaderID, GLShaderStatus.COMPILE);
        } catch (ShaderException e) {
            return UNABLE_TO_GENERATE;
        }

        return shaderID;
    }

    @Override
    protected void nativeDelete() {
        adapter.deleteShader(id());
    }

    /**
     * Attachs this shader to the given Shader Program
     *
     * @param program The shader program
     */
    final void attach(int program) {
        if (!isGen()) {
            throw new ShaderException("Cannot attach a shader to program when shader is generated!");
        }

        adapter.attachShader(program, id());
    }

    /**
     * Detaches this shader from the given Shader Program
     *
     * @param program The shader program
     */
    final void detach(int program) {
        if (!isGen()) {
            throw new ShaderException("Cannot detach a shader from program when shader is generated!");
        }

        adapter.detachShader(program, id());
    }

    /**
     * @return The type of shader this object represents
     */
    public final GLShaderType getType() {
        return this.type;
    }
}
