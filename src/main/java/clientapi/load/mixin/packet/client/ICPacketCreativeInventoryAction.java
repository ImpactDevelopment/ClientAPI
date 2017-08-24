package clientapi.load.mixin.packet.client;

import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:28 PM
 */
@Mixin(CPacketCreativeInventoryAction.class)
public interface ICPacketCreativeInventoryAction {

    @Accessor int getSlotId();

    @Accessor void setSlotId(int slotId);

    @Accessor ItemStack getStack();

    @Accessor void setStack(ItemStack stack);
}
