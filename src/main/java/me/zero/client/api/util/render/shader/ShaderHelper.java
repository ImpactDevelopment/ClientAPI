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

import me.zero.client.api.util.io.StreamReader;
import me.zero.client.api.util.render.shader.adapter.ShaderAdapter;
import me.zero.client.api.util.render.shader.glenum.GlShaderStatus;
import me.zero.client.api.util.render.shader.glenum.GlShaderType;

/**
 * Utils for Shader Programs
 *
 * @author Brady
 * @since 2/16/2017 12:00 PM
 */
final class ShaderHelper {

    private ShaderHelper() {}

    /**
     * Loads a shader of the specified type from the specified path
     *
     * @param path Shader path
     * @param type Shader type
     * @return The Shader's Object ID
     */
    static int loadShader(ShaderAdapter adapter, String path, GlShaderType type) {
        int shaderID = adapter.createShader(type);
        if (shaderID == 0)
            return 0;

        String src = new StreamReader(Shader.class.getResourceAsStream(path)).read();
        adapter.shaderSource(shaderID, src);
        adapter.compileShader(shaderID);
        adapter.checkStatus(shaderID, GlShaderStatus.COMPILE);
        return shaderID;
    }
}
