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

package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;
import net.minecraft.network.Packet;

/**
 * Called whenever a packet is either sent or received
 *
 * @see Send
 * @see Receive
 *
 * @author Brady
 * @since 2/7/2017 12:00 PM
 */
public class PacketEvent extends Cancellable {

    /**
     * The packet being sent
     */
    private Packet<?> packet;

    private PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    /**
     * @return The packet being sent/received
     */
    public final Packet<?> getPacket() {
        return this.packet;
    }

    /**
     * Sets the packet that is being either sent
     * or received to the specified packet.
     *
     * @param packet The new packet
     * @return This event
     */
    public final Packet<?> setPacket(Packet<?> packet) {
        return this.packet = packet;
    }

    /**
     * Called when a packet is being sent from the client to server
     */
    public static final class Send extends PacketEvent {

        public Send(Packet<?> packet) {
            super(packet);
        }
    }

    /**
     * Called when a packet is being received from the server to the client
     */
    public static final class Receive extends PacketEvent {

        public Receive(Packet<?> packet) {
            super(packet);
        }
    }
}
