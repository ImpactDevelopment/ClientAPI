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

package clientapi.event.defaults.game.render;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;

/**
 * Called before the overlay rendering is setup, will call
 * regardless of the {@code GameSettings#hideGUI} value.
 *
 * @see EntityRenderer#updateCameraAndRender(float, long)
 *
 * @author Brady
 * @since 4/30/2017 12:00 PM
 */
public final class RenderScreenEvent extends RenderEvent {

    /**
     * The inner window width of the display
     */
    private final int width;

    /**
     * The inner window height of the display
     */
    private final int height;

    /**
     * Whether or not hide gui is enabled
     *
     * @see GameSettings#hideGUI
     */
    private final boolean guiHidden;

    public RenderScreenEvent(float partialTicks) {
        super(partialTicks);
        this.width = mc.displayWidth;
        this.height = mc.displayHeight;
        this.guiHidden = mc.gameSettings.hideGUI;
    }

    /**
     * @return The inner window width of the display
     */
    public final int getWidth() {
        return this.width;
    }

    /**
     * @return The inner window height of the display
     */
    public final int getHeight() {
        return this.height;
    }

    /**
     * @return Whether or not hide gui is enabled
     */
    public final boolean isGuiHidden() {
        return this.guiHidden;
    }

    @Override
    public String toString() {
        return "RenderScreenEvent{" +
                "width=" + width +
                ", height=" + height +
                ", guiHidden=" + guiHidden +
                ", partialTicks=" + partialTicks +
                '}';
    }
}
