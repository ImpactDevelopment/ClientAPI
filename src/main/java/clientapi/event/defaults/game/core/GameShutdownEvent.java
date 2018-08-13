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

package clientapi.event.defaults.game.core;

import me.zero.alpine.type.Cancellable;
import net.minecraft.client.Minecraft;

/**
 * Called when the game is requested to be shutdown.
 * This event is invoked at the head of Minecraft#shutdown().
 * Cancelling this event will result in the shutdown
 * process cancelling. The only reason a developer should
 * cancel shutdown is to complete a cleanup process,
 * if it is unable to be done directly before the shutdown.
 *
 * @see Minecraft#shutdown()
 *
 * @author Brady
 * @since 5/24/2017
 */
public final class GameShutdownEvent extends Cancellable {

    @Override
    public String toString() {
        return "GameShutdownEvent{}";
    }
}
