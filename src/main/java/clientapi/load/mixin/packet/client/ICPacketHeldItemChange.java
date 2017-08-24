package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketHeldItemChange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:46 PM
 */
@Mixin(CPacketHeldItemChange.class)
public interface ICPacketHeldItemChange {

    @Accessor int getSlotId();

    @Accessor void setSlotId(int slotId);
}
