package me.zero.client.load.mixin.wrapper;

import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
@Mixin(KeyBinding.class)
public interface IKeyBinding {

    @Accessor void setPressed(boolean pressed);
}
