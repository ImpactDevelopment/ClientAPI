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

package clientapi.event.defaults.game.world;

import me.zero.alpine.type.Cancellable;
import net.minecraft.block.Block;

/**
 * Called when the collision state of a block is checked.
 * When the event is cancelled, the collision check returns
 * false.
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class BlockCollisionEvent extends Cancellable {

    /**
     * Block receiving collision check
     */
    private final Block block;

    public BlockCollisionEvent(Block block) {
        this.block = block;
    }

    /**
     * @return The block receiving a collision check
     */
    public final Block getBlock() {
        return this.block;
    }
}
