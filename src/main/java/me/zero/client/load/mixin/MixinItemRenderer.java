package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.ItemRenderEvent;
import me.zero.client.wrapper.IItemRenderer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer implements IItemRenderer {

    @Shadow public abstract void transformSideFirstPerson(EnumHandSide side, float rechargeProgress);
    @Shadow public abstract void transformFirstPerson(EnumHandSide side, float swingProgress);

    @Inject(method = "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V", at = @At("HEAD"), cancellable = true)
    public void renderItemInFirstPerson(AbstractClientPlayer p_187457_1_, float p_187457_2_, float p_187457_3_, EnumHand p_187457_4_, float p_187457_5_, ItemStack p_187457_6_, float p_187457_7_, CallbackInfo ci) {
        ItemRenderEvent event = new ItemRenderEvent((ItemRenderer) (Object) this, p_187457_2_, p_187457_4_, p_187457_5_, p_187457_6_, p_187457_7_);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Override
    public void setupSideFirstPerson(EnumHandSide side, float rechargeProgress) {
        this.transformSideFirstPerson(side, rechargeProgress);
    }

    @Override
    public void setupFirstPerson(EnumHandSide side, float swingProgress) {
        this.transformFirstPerson(side, swingProgress);
    }
}
