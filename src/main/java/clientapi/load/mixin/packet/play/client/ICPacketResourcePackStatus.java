package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketResourcePackStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:34 PM
 */
@Mixin(CPacketResourcePackStatus.class)
public interface ICPacketResourcePackStatus {

    @Accessor CPacketResourcePackStatus.Action getAction();

    @Accessor void setAction(CPacketResourcePackStatus.Action action);
}
