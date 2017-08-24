package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketClientStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:18 PM
 */
@Mixin(CPacketClientStatus.class)
public interface ICPacketClientStatus {

    @Accessor CPacketClientStatus.State getStatus();

    @Accessor void setStatus(CPacketClientStatus.State status);
}
