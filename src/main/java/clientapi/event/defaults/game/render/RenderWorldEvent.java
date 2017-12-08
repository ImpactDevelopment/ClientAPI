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

package clientapi.event.defaults.game.render;

/**
 * Called at the end of EntityRenderer#renderWorldPass(int, float, long)
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class RenderWorldEvent extends RenderEvent {

    /**
     * The Render pass this event was called from
     */
    private final Pass pass;

    public RenderWorldEvent(float partialTicks, int pass) {
        super(partialTicks);
        this.pass = Pass.values()[pass];
    }

    /**
     * @return The render pass that this event was called from
     */
    public final Pass getPass() {
        return this.pass;
    }

    public enum Pass {
        ANAGLYPH_CYAN, ANAGLYPH_RED, NORMAL
    }
}
