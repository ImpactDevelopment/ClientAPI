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

import clientapi.event.defaults.game.core.UpdateEvent;
import clientapi.module.Mod;
import clientapi.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.example.mod.category.IMovement;
import org.lwjgl.glfw.GLFW;

/**
 * @author Brady
 * @since 2/8/2017 12:00 PM
 */
@Mod(name = "Fly", description = "Allows you to fly", bind = GLFW.GLFW_KEY_F)
public final class Fly extends Module implements IMovement {

    @EventHandler
    private Listener<UpdateEvent> updateListener = new Listener<>(event ->
        mc.player.motionY = Boolean.compare(mc.gameSettings.keyBindJump.isKeyDown(), mc.gameSettings.keyBindSneak.isKeyDown()) * 0.2
    );
}
