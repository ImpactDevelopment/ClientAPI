package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:32 PM
 */
@Mixin(CPacketCustomPayload.class)
public interface ICPacketCustomPayload {

    @Accessor String getChannel();

    @Accessor void setChannel(String channel);

    @Accessor PacketBuffer getData();

    @Accessor void setData(PacketBuffer data);
}
