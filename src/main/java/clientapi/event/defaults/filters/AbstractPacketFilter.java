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

package clientapi.event.defaults.filters;

import clientapi.event.defaults.game.network.PacketEvent;
import net.minecraft.network.Packet;

import java.util.function.Predicate;

/**
 * An abstract filter for Packet Events that must have its predicate method implemented.
 *
 * @author Brady
 * @since 9/17/2018
 */
public abstract class AbstractPacketFilter<T extends PacketEvent> implements Predicate<T> {

    /**
     * Packets allowed by this filter
     */
    protected final Class<? extends Packet<?>>[] packets;

    @SafeVarargs
    public AbstractPacketFilter(Class<? extends Packet<?>>... packets) {
        this.packets = packets;
    }
}
