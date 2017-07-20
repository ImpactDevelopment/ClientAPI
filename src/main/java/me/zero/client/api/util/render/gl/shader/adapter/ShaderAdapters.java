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

import org.lwjgl.opengl.GLContext;

/**
 * Used to retrieve the system shader adapter instance
 *
 * @author Brady
 * @since 5/21/2017 12:27 PM
 */
public final class ShaderAdapters {

    /**
     * System supported shader adapter
     */
    private static final ShaderAdapter systemDefault;

    private ShaderAdapters() {}

    static {
        // Check if OpenGL 2.0 is supported by the machine
        if (GLContext.getCapabilities().OpenGL20)
            systemDefault = new GL20ShaderAdapter();
        else
            systemDefault = new ARBShaderAdapter();
    }

    /**
     * Returns the discovered system compatible shader adapter.
     * The adapter is determined by checking OpenGL version
     * compatibility.
     *
     * @return System supported shader adapter
     */
    public static ShaderAdapter getSystemAdapter() {
        return systemDefault;
    }
}
