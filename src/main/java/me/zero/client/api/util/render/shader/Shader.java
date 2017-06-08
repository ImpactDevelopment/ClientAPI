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

package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.render.shader.adapter.ShaderAdapter;
import me.zero.client.api.util.render.shader.adapter.ShaderAdapters;
import me.zero.client.api.util.render.shader.glenum.GlShaderStatus;
import me.zero.client.api.util.render.shader.glenum.GlShaderType;

import java.util.HashMap;
import java.util.Map;

import static me.zero.client.api.util.render.shader.ShaderHelper.*;

/**
 * Used to create ARB Shader Programs with OpenGL
 *
 * @author Brady
 * @since 2/16/2017 12:00 PM
 */
public abstract class Shader implements Helper {

    /**
     * Instance of the system supported shader adapter
     */
    private final ShaderAdapter adapter = ShaderAdapters.getSystemAdapter();

    /**
     * The uniform variables for this shader
     */
    private final Map<String, Uniform> uniforms = new HashMap<>();

    /**
     * Various Object IDs
     */
    private final int programID, fragmentID, vertexID;

    public Shader(String vertex, String fragment) {
        programID = adapter.createProgram();
        vertexID = loadShader(adapter, vertex, GlShaderType.VERTEX);
        fragmentID = loadShader(adapter, fragment, GlShaderType.FRAGMENT);

        adapter.attachObject(programID, vertexID);
        adapter.attachObject(programID, fragmentID);

        adapter.linkProgram(programID);
        adapter.checkStatus(programID, GlShaderStatus.LINK);

        adapter.validateProgram(programID);
        adapter.checkStatus(programID, GlShaderStatus.VALIDATE);
    }

    /**
     * Attaches the shader program
     */
    public final void attach() {
        adapter.useProgram(programID);
        update();
    }

    /**
     * Detaches the shader program
     */
    public final void detach() {
        adapter.useProgram(0);
    }

    /**
     * Called after the shader program is
     * attached to update the uniform vars
     */
    public abstract void update();

    /**
     * Deletes this ShaderProgram
     */
    public final void delete() {
        detach();
        adapter.detachObject(programID, vertexID);
        adapter.detachObject(programID, fragmentID);
        adapter.deleteShader(vertexID);
        adapter.deleteShader(fragmentID);
        adapter.deleteProgram(programID);
    }

    /**
     * Gets a uniform variable from the specified name
     *
     * @param name The Uniform Name
     * @return The Uniform Variable
     */
    protected final Uniform getUniform(String name) {
        return uniforms.computeIfAbsent(name, n -> Uniform.get(programID, n));
    }
}
