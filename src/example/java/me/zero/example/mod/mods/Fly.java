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

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import clientapi.event.defaults.game.core.UpdateEvent;
import clientapi.module.Mod;
import clientapi.module.Module;
import me.zero.example.mod.category.IMovement;
import org.lwjgl.input.Keyboard;

/**
 * Created by Brady on 2/8/2017.
 */
@Mod(name = "Fly", description = "Allows you to fly", bind = Keyboard.KEY_F)
public final class Fly extends Module implements IMovement {

    @EventHandler
    private Listener<UpdateEvent> updateListener = new Listener<>(event ->
        mc.player.motionY = Boolean.valueOf(mc.gameSettings.keyBindJump.isKeyDown()).compareTo(mc.gameSettings.keyBindSneak.isKeyDown()) * 0.2
    );
}
