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

package clientapi.command.sender;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Implementation of {@code CommandSender}, where the sender
 * is defined by an {@code EntityPlayer}
 *
 * @author Brady
 * @since 10/17/2017 9:17 PM
 */
public interface PlayerSender extends CommandSender {

    /**
     * @return {@code EntityPlayer} representing this sender.
     */
    EntityPlayer getPlayer();

    /**
     * Implementation of {@code PlayerSender}
     */
    class Impl implements PlayerSender {

        private final EntityPlayer player;

        Impl(EntityPlayer player) {
            this.player = player;
        }

        @Override
        public final EntityPlayer getPlayer() {
            return this.player;
        }

        @Override
        public final String getName() {
            return this.player.getName().getString();
        }
    }
}
