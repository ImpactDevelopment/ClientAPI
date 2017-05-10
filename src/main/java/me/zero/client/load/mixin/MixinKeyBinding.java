package me.zero.client.load.mixin;

import me.zero.client.wrapper.IKeyBinding;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(KeyBinding.class)
public class MixinKeyBinding implements IKeyBinding {

    @Shadow private boolean pressed;

    @Override
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
