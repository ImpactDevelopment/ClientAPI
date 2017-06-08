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

package me.zero.client.api.event.defaults;

import me.zero.client.api.util.interfaces.Helper;

/**
 * Called at the end of EntityRenderer#renderWorldPass(int, float, long)
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class Render3DEvent implements Helper {

    /**
     * The render partial ticks
     */
    private final float partialTicks;

    public Render3DEvent() {
        this.partialTicks = mca.getTimer().renderPartialTicks;
    }

    /**
     * @return The render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }
}
