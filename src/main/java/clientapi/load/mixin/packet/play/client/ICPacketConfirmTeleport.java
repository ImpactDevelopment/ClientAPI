package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketConfirmTeleport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:21 PM
 */
@Mixin(CPacketConfirmTeleport.class)
public interface ICPacketConfirmTeleport {

    @Accessor("telportId") int getTeleportId();

    @Accessor("telportId") void setTeleportId(int teleportId);
}
