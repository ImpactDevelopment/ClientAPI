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

package clientapi.command.sender;

import net.minecraft.client.network.NetworkPlayerInfo;

/**
 * @author Brady
 * @since 10/17/2017 9:29 PM
 */
public interface RemotePlayerSender extends CommandSender {

    /**
     * @return {@code NetworkPlayerInfo} representing this sender.
     */
    NetworkPlayerInfo getPlayer();

    /**
     * Implementation of {@code PlayerSender}
     */
    class Impl implements RemotePlayerSender {

        private final NetworkPlayerInfo player;

        Impl(NetworkPlayerInfo player) {
            this.player = player;
        }

        @Override
        public final NetworkPlayerInfo getPlayer() {
            return this.player;
        }

        @Override
        public final String getName() {
            return this.player.getGameProfile().getName();
        }
    }
}
