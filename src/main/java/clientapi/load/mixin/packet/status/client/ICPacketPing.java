package clientapi.load.mixin.packet.status.client;

import net.minecraft.network.status.client.CPacketPing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 10:17 PM
 */
@Mixin(CPacketPing.class)
public interface ICPacketPing {

    @Accessor long getClientTime();

    @Accessor void setClientTime(long clientTime);
}
