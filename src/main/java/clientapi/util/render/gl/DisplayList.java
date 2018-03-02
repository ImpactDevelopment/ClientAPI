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

import net.minecraft.client.renderer.GlStateManager;

/**
 * Display lists are used to capture OpenGL instructions.
 * These instructions can then later be called upon. This
 * is generally more efficient then repeating the
 * instructions multiple times in the code directly.
 *
 * @author Brady
 * @since 7/22/2017 4:27 PM
 */
public final class DisplayList extends GLObject {

    /**
     * The number of contiguous empty display lists to be generated.
     * In most cases, '1' will suffice.
     */
    private final int range;

    public DisplayList(int range) {
        this.range = range;
    }

    @Override
    protected final int nativeGen() {
        return GlStateManager.glGenLists(range);
    }

    @Override
    protected final void nativeDelete() {
        GlStateManager.glDeleteLists(id(), range);
    }

    /**
     * Begins capturing all subsequent instructions
     *
     * @see #stop()
     *
     * @param mode Instruction capture mode
     */
    public final void start(int mode) {
        GlStateManager.glNewList(id(), mode);
    }

    /**
     * Stops capturing all instructions since start
     *
     * @see #start(int)
     */
    public final void stop() {
        GlStateManager.glEndList();
    }

    /**
     * Calls all instructions that have been captured by this list
     */
    public final void call() {
        GlStateManager.callList(id());
    }
}
