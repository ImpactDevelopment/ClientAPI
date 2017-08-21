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

import net.minecraft.network.*;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Brady
 * @since 7/16/2017 5:22 PM
 */
@Mixin(NettyPacketEncoder.class)
public class MixinNettyPacketEncoder {

	@Shadow
	@Final
	private static Logger LOGGER;
	@Shadow
	@Final
	private static Marker RECEIVED_PACKET_MARKER;
	@Shadow
	@Final
	private EnumPacketDirection direction;

	/**
	 * @reason mostly because we need to mutate msg, but also so that we can
	 *         pass the connection state to the event constructor
	 * @author Brady
	 */
	@Overwrite
	protected void encode(ChannelHandlerContext ctx, Packet<?> msg, ByteBuf out)
	    throws Exception {
		EnumConnectionState state =
		    ctx.channel().attr(NetworkManager.PROTOCOL_ATTRIBUTE_KEY).get();
		Integer packetId = state.getPacketId(this.direction, msg);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(RECEIVED_PACKET_MARKER, "OUT: [{}:{}] {}", state,
			    packetId, msg.getClass().getName());
		}

		// We need the state and we plan to mutate the msg, hence the overwrite
		PacketEvent event = new PacketEvent.Encode(msg, state);
		ClientAPI.EVENT_BUS.post(event);
		msg = event.getPacket();
		if (event.isCancelled() || msg == null) return;

		if (packetId == null) {
			throw new IOException("Can\'t serialize unregistered packet");
		} else {
			PacketBuffer buffer = new PacketBuffer(out);
			buffer.writeVarInt(packetId);

			try {
				msg.writePacketData(buffer);
			} catch (Throwable throwable) {
				LOGGER.error(throwable);
			}
		}
	}
}
