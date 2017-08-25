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

package me.zero.example.mod.mods;

import clientapi.event.defaults.game.render.RenderHudEvent;
import clientapi.module.Mod;
import clientapi.module.Module;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.example.ExampleClient;
import me.zero.example.mod.category.IRender;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Comparator;

/**
 * Created by Brady on 2/8/2017.
 */
@Mod(name = "HUD", description = "Displays an In-Game HUD")
public final class Hud extends Module implements IRender {

    private int y;

    public Hud() {
        // Force Hud to enable on start
        this.setState(true);
    }

    @EventHandler
    private Listener<RenderHudEvent> render2DListener =
        new Listener<>(event -> {
            ScaledResolution sr = new ScaledResolution(mc);
            FontRenderer font = mc.fontRenderer;

            int color = 0xFF98ABB8;

            font.drawStringWithShadow("Client", 2, 2, color);

            y = sr.getScaledHeight() - 12;

            ExampleClient.getInstance().getModuleManager().getData().stream()
                .filter(Module::getState)
                .sorted(Comparator
                    .comparingInt(m -> -font.getStringWidth(m.getName())))
                .forEach(module -> {
                    font.drawStringWithShadow(module.getName(),
                        sr.getScaledWidth() - 2
                            - font.getStringWidth(module.getName()),
                        y, color);
                    y -= font.FONT_HEIGHT;
                });
        });
}
