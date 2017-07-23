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

package me.zero.client.api.util.render.gl;

import me.zero.client.api.exception.ShaderException;
import me.zero.client.api.util.render.gl.glenum.GLShaderStatus;
import me.zero.client.api.util.render.gl.glenum.GLShaderType;
import me.zero.client.api.util.render.gl.shader.adapter.ShaderAdapter;
import me.zero.client.api.util.render.gl.shader.adapter.ShaderAdapters;

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
            return 0;

        try {
            adapter.shaderSource(shaderID, src);
            adapter.compileShader(shaderID);
            adapter.checkStatus(shaderID, GLShaderStatus.COMPILE);
        } catch (ShaderException e) {
            return 0;
        }

        return shaderID;
    }

    @Override
    protected void nativeDelete() {
        adapter.deleteShader(id());
    }

    /**
     * @return The type of shader this object represents
     */
    public final GLShaderType getType() {
        return this.type;
    }
}
