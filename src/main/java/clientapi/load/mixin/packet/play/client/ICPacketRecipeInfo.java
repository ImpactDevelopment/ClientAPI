package clientapi.load.mixin.packet.play.client;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.client.CPacketRecipeInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:28 PM
 */
@Mixin(CPacketRecipeInfo.class)
public interface ICPacketRecipeInfo {

    @Accessor CPacketRecipeInfo.Purpose getPurpose();

    @Accessor void setPurpose(CPacketRecipeInfo.Purpose purpose);

    @Accessor IRecipe getRecipe();

    @Accessor void setRecipe(IRecipe recipe);

    @Accessor("isGuiOpen") boolean isGuiOpen();

    @Accessor("isGuiOpen") void setGuiOpen(boolean isGuiOpen);

    @Accessor boolean isFilteringCraftable();

    @Accessor void setFilteringCraftable(boolean filteringCraftable);
}
