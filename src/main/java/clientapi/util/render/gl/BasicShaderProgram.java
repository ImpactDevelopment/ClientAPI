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

import clientapi.util.render.gl.glenum.GLShaderType;

/**
 * Implementation of {@code ShaderProgram} for more
 * basic uses. (Ones that only require a vertex and
 * fragment shader. Automatically compiles both the
 * vertex and fragment shader, along with generating
 * this shader program.
 *
 * @author Brady
 * @since 9/6/2017 4:43 PM
 */
public class BasicShaderProgram extends ShaderProgram {

    /**
     * Creates an instance of {@code BasicShaderProgram}
     *
     * @param vSource Source code of the vertex shader
     * @param fSource Source code of the fragment shader
     */
    public BasicShaderProgram(String vSource, String fSource) {
        Shader vertex = new Shader(GLShaderType.VERTEX, vSource);
        vertex.gen();
        this.attachShader(vertex);

        Shader fragment = new Shader(GLShaderType.FRAGMENT, fSource);
        fragment.gen();
        this.attachShader(fragment);

        this.gen();
    }
}
