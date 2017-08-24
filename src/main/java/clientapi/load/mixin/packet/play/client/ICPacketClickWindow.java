package clientapi.load.mixin.packet.play.client;

import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketClickWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:03 PM
 */
@Mixin(CPacketClickWindow.class)
public interface ICPacketClickWindow {

    @Accessor int getWindowId();

    @Accessor void setWindowId(int windowId);

    @Accessor int getSlotId();

    @Accessor void setSlotId(int slotId);

    @Accessor int getPackedClickData();

    @Accessor void setPackedClickData(int packetClickData);

    @Accessor short getActionNumber();

    @Accessor void setActionNumber(short actionNumber);

    @Accessor ItemStack getClickedItem();

    @Accessor void setClickedItem(ItemStack clickedItem);

    @Accessor ClickType getMode();

    @Accessor void setMode(ClickType mode);
}
