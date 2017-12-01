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

package clientapi.load.mixin.extension;

import net.minecraft.util.Session;
import net.minecraft.util.Timer;

/**
 * @author Brady
 * @since 2/20/2017 12:00 PM
 */
public interface IMinecraft {

    /**
     * @return Returns the Game's Timer
     */
    Timer getTimer();

    /**
     * Sets the game's session
     *
     * @param session The new Session
     */
    void setSession(Session session);

    /**
     * Sets the right click delay timer
     *
     * @param delay The new right click delay
     */
    void setRightClickDelayTimer(int delay);

    /**
     * Clicks a mouse button
     *
     * @param button The button to click (LEFT, MIDDLE, RIGHT)
     */
    void clickMouse(int button);

}
