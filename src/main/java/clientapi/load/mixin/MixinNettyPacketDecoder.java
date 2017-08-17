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

package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.network.PacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.util.List;

/**
 * @author Brady
 * @since 7/17/2017 2:22 PM
 */
@Mixin(NettyPacketDecoder.class)
public class MixinNettyPacketDecoder {

    @Shadow @Final private static Logger LOGGER;
    @Shadow @Final private static Marker RECEIVED_PACKET_MARKER;
    @Shadow @Final private EnumPacketDirection direction;

    /**
     * @reason we need to wait until the packet is built and then pass it (and the connection state) to the event constructor
     * @author Brady
     */
    @Overwrite
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IOException, InstantiationException, IllegalAccessException {
        if (in.readableBytes() == 0)
            return;

        PacketBuffer packetbuffer = new PacketBuffer(in);
        int packetId = packetbuffer.readVarInt();
        EnumConnectionState state = ctx.channel().attr(NetworkManager.PROTOCOL_ATTRIBUTE_KEY).get();
        Packet<?> packet = state.getPacket(this.direction, packetId);

        // We need the packet to create our Event, so we have to overwrite the method body
        PacketEvent event = new PacketEvent.Decode(packet, state);
        ClientAPI.EVENT_BUS.post(event);
        packet = event.getPacket();
        if (event.isCancelled())
            return;

        if (packet == null) {
            throw new IOException(String.format("Bad packet id %s", packetId));
        } else {
            packet.readPacketData(packetbuffer);

            if (packetbuffer.readableBytes() > 0) {
                throw new IOException(String.format("Packet %s/%s (%s) was larger than I expected, found %s bytes extra whilst reading packet %s", state.getId(), packetId, packet.getClass().getSimpleName(), packetbuffer.readableBytes(), packetId));
            } else {
                out.add(packet);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(RECEIVED_PACKET_MARKER, " IN: [{}:{}] {}", state, packetId, packet.getClass().getName());
                }
            }
        }
    }
}
