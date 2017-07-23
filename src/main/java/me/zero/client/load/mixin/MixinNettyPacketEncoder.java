package me.zero.client.load.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.game.network.PacketEvent;
import net.minecraft.network.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

/**
 * @author Brady
 * @since 7/16/2017 5:22 PM
 */
@Mixin(NettyPacketEncoder.class)
public class MixinNettyPacketEncoder {

    @Shadow @Final private static Logger LOGGER;
    @Shadow @Final private static Marker RECEIVED_PACKET_MARKER;
    @Shadow @Final private EnumPacketDirection direction;

    /**
     * @author Brady
     */
    @Overwrite
    protected void encode(ChannelHandlerContext ctx, Packet<?> msg, ByteBuf out) throws IOException {
        EnumConnectionState state = ctx.channel().attr(NetworkManager.PROTOCOL_ATTRIBUTE_KEY).get();
        Integer packetId = state.getPacketId(this.direction, msg);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RECEIVED_PACKET_MARKER, "OUT: [{}:{}] {}", state, packetId, msg.getClass().getName());
        }

        PacketEvent event = new PacketEvent.Encode(msg, state);
        ClientAPI.EVENT_BUS.post(event);
        msg = event.getPacket();
        if (event.isCancelled() || msg == null)
            return;

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
