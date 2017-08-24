package clientapi.load.mixin.packet.play.client;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.client.CPacketPlaceRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:56 PM
 */
@Mixin(CPacketPlaceRecipe.class)
public interface ICPacketPlaceRecipe {

    @Accessor("field_194320_a") int getWindowId();

    @Accessor("field_194320_a") void setWindowId(int windowId);

    @Accessor("field_194321_b") IRecipe getLastClickedRecipe();

    @Accessor("field_194321_b") void setLastClickedRecipe(IRecipe lastClickedRecipe);

    @Accessor("field_194322_c") boolean isCraftStack();

    @Accessor("field_194322_c") void setCraftStack(boolean craftStack);
}
