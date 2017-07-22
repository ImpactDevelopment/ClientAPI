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

package me.zero.client.api.util.render.gl.object;

import me.zero.client.api.util.render.gl.GlObject;
import me.zero.client.api.util.render.gl.glenum.GlListMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Brady
 * @since 7/22/2017 4:27 PM
 */
public final class DisplayList extends GlObject {

    private final int range;

    public DisplayList(int range) {
        this.range = range;
    }

    @Override
    protected final int nativeGen() {
        return glGenLists(range);
    }

    @Override
    protected final void nativeDelete() {
        glDeleteLists(id(), range);
    }

    public final void start(GlListMode mode) {
        glNewList(id(), mode.id);
    }

    public final void stop() {
        glEndList();
    }

    public final void call() {
        glCallList(id());
    }
}
