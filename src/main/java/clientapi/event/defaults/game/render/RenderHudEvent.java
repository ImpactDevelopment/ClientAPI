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

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Called after the in-game overlay is finished rendering.
 *
 * @see GuiIngame#renderGameOverlay(float)
 *
 * @author Brady
 * @since 2/6/2017 12:00 PM
 */
public final class RenderHudEvent extends RenderEvent {

    /**
     * Instance of the current {@code ScaledResolution}
     */
    private final ScaledResolution scaledResolution;

    public RenderHudEvent(float partialTicks) {
        super(partialTicks);
        this.scaledResolution = new ScaledResolution(mc);
    }

    /**
     * @return Instance of the current {@code ScaledResolution}
     */
    public final ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
}
