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

package clientapi.api.util.render.gl;

import clientapi.api.exception.ShaderException;
import clientapi.api.util.render.gl.glenum.GLShaderType;
import clientapi.api.util.render.gl.shader.Uniform;
import clientapi.api.util.render.gl.glenum.GLShaderStatus;
import clientapi.api.util.render.gl.shader.adapter.ShaderAdapter;
import clientapi.api.util.render.gl.shader.adapter.ShaderAdapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brady
 * @since 7/23/2017 3:31 PM
 */
public class ShaderProgram extends GLObject {

    /**
     * Instance of the system supported shader adapter
     */
    private static final ShaderAdapter adapter = ShaderAdapters.getSystemAdapter();

    /**
     * The uniform variables for this shader program
     */
    private final Map<String, Uniform> uniforms = new HashMap<>();

    /**
     * The shaders attached to this program
     */
    private final List<Shader> shaders = new ArrayList<>();

    @Override
    protected final int nativeGen() {
        int id = adapter.createProgram();
        shaders.forEach(shader -> adapter.attachShader(id, shader.id()));

        try {
            adapter.linkProgram(id);
            adapter.checkStatus(id, GLShaderStatus.LINK);
            adapter.validateProgram(id);
            adapter.checkStatus(id, GLShaderStatus.VALIDATE);
        } catch (ShaderException e) {
            e.printStackTrace();
            return 0;
        }

        return id;
    }

    @Override
    public boolean delete() {
        shaders.forEach(shader -> {
            adapter.detachShader(this.id(), shader.id());
            shader.delete();
        });
        return super.delete();
    }

    @Override
    protected final void nativeDelete() {
        adapter.deleteProgram(id());
    }

    /**
     * Attaches the shader program. Multiple shader
     * programs may not be attached simultaneously.
     */
    public final void attach() {
        adapter.useProgram(id());
        update();
    }

    /**
     * Detaches the shader program
     */
    public final void detach() {
        adapter.useProgram(0);
    }

    /**
     * May be overriden by implementations of this class. Used to
     * update uniform variable values when the shader is attached.
     */
    protected void update() {}

    /**
     * Attaches a shader to this shader program. This may only be
     * done before the shader has been compiled via the gen() method
     * and there isn't already a shader with the same type.
     *
     * @param shader The shader
     * @return Whether or not the operation was a success
     */
    public final boolean attachShader(Shader shader) {
        if (isGen() || getShader(shader.getType()) != null)
            return false;

        shaders.add(shader);
        return true;
    }

    /**
     * Finds and returns a shader attached to this program
     * with the specified type, if there is one. Returns 'null'
     * if a shader isn't found.
     *
     * @param type The type of shader
     * @return The shader with the specified type
     */
    public final Shader getShader(GLShaderType type) {
        return shaders.stream().filter(shader -> shader.getType() == type).findFirst().orElse(null);
    }

    /**
     * Gets a uniform variable from the specified name
     *
     * @param name The Uniform Name
     * @return The Uniform Variable
     */
    protected final Uniform getUniform(String name) {
        return uniforms.computeIfAbsent(name, n -> Uniform.get(id(), n));
    }
}
