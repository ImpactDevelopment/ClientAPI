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

package clientapi.event.defaults.game.network;

import me.zero.alpine.type.Cancellable;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.login.server.SPacketEncryptionRequest;

/**
 * Fired when the server we are connecting to requests us to authenticate with Mojang
 *
 * @since 3.0
 */
public final class EncryptionRequestEvent extends Cancellable {

    private final NetHandlerLoginClient net;
    private final SPacketEncryptionRequest packet;

    /**
     * @param hetHandler the NetHandlerLoginClient object that received the request
     * @param packet the encryption request packet
     */
    public EncryptionRequestEvent(NetHandlerLoginClient hetHandler, SPacketEncryptionRequest packet) {
        this.net = hetHandler;
        this.packet = packet;
    }

    /**
     * @return the NetHandlerLoginClient object that received the request
     */
    public NetHandlerLoginClient getNetHandler() {
        return this.net;
    }

    /**
     * @return the encryption request packet
     */
    public SPacketEncryptionRequest getPacket() {
        return packet;
    }
}
