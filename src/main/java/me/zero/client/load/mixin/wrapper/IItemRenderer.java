package me.zero.client.load.mixin.wrapper;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
@Mixin(ItemRenderer.class)
public interface IItemRenderer {

    @Invoker void transformSideFirstPerson(EnumHandSide side, float rechargeProgress);

    @Invoker void transformFirstPerson(EnumHandSide side, float swingProgress);
}
