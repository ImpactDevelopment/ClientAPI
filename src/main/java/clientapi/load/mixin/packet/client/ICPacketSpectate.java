package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketSpectate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

/**
 * @author Brady
 * @since 8/23/2017 9:38 PM
 */
@Mixin(CPacketSpectate.class)
public interface ICPacketSpectate {

    @Accessor UUID getId();

    @Accessor void setId(UUID id);
}
