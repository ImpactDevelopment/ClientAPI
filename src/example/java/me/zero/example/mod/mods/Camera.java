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

package me.zero.example.mod.mods;

import clientapi.event.defaults.game.render.RenderHudEvent;
import clientapi.module.Mod;
import clientapi.module.Module;
import clientapi.util.annotation.Label;
import clientapi.util.render.camera.defaults.OverheadCamera;
import clientapi.value.annotation.NumberValue;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.type.EventPriority;
import me.zero.example.mod.category.IRender;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

/**
 * @author Brady
 * @since 2/11/2017 12:00 PM
 */
@Mod(name = "Camera", description = "", bind = Keyboard.KEY_B)
public final class Camera extends Module implements IRender {

    @Label(name = "Height", id = "offset", description = "How high up the camera is")
    @NumberValue(min = 10, max = 50)
    private double height = 30;

    // Must be instantiated before the Render2DEvent Listener
    private OverheadCamera camera = new OverheadCamera(new OverheadCamera.OverheadHandle() {

        @Override
        public final float camRotation() {
            return mc.player.rotationYaw;
        }

        @Override
        public final double camHeight() {
            return height;
        }

        @Override
        public final boolean visible() {
            return Camera.this.getState();
        }

        @Override
        public final boolean reflected() {
            return false;
        }

        @Override
        public final int width() {
            return (int) (new ScaledResolution(mc).getScaledWidth() / 5F);
        }

        @Override
        public final int height() {
            return (int) (new ScaledResolution(mc).getScaledWidth() / 5F);
        }
    });

    @EventHandler
    private Listener<RenderHudEvent> render2DListener = new Listener<>(event -> {
        height = 10;
        ScaledResolution sr = event.getScaledResolution();
        float size = sr.getScaledWidth() / 5F;
        camera.draw(sr.getScaledWidth() - size, 0, sr.getScaledWidth(), size);
    }, EventPriority.HIGHEST);
}
