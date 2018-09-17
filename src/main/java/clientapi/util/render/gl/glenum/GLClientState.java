/*
 * Copyright 2018 ImpactDevelopment
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

package clientapi.util.render.gl.glenum;

import static org.lwjgl.opengl.GL11.*;

/**
 * Contains all valid GLenum ClientState caps.
 *
 * @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glEnableClientState.xml">glEnableClientState</a>
 * @see <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glDisableClientState.xml">glDisableClientState</a>
 */
public enum GLClientState implements GLenum {

    COLOR  ("GL_COLOR_ARRAY",         GL_COLOR_ARRAY),
    TEXTURE("GL_TEXTURE_COORD_ARRAY", GL_TEXTURE_COORD_ARRAY),
    VERTEX ("GL_VERTEX_ARRAY",        GL_VERTEX_ARRAY);

    private final String name;
    private final int cap;

    GLClientState(String name, int cap) {
        this.name = name;
        this.cap = cap;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCap() {
        return this.cap;
    }
}
