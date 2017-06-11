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

package me.zero.client.load.mixin.wrapper;

import me.zero.client.api.event.defaults.ClickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * @author Brady
 * @since 2/20/2017 12:00 PM
 */
@Mixin(Minecraft.class)
public interface IMinecraft {

    /**
     * @return Returns the Game's Timer
     */
    @Accessor Timer getTimer();

    /**
     * Sets the game's session
     *
     * @param session The new Session
     */
    @Accessor void setSession(Session session);

    /**
     * Sets the right click delay timer
     *
     * @param delay The new right click delay
     */
    @Accessor void setRightClickDelayTimer(int delay);

    @Invoker("clickMouse") void leftClickMouse();
    @Invoker("rightClickMouse") void rightClickMouse();
    @Invoker("middleClickMouse") void middleClickMouse();

    /**
     * Clicks a mouse button
     *
     * @param button The button
     */
    void clickMouse(ClickEvent.MouseButton button);
}
