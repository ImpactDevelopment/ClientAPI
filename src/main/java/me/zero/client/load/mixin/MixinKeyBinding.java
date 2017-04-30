package me.zero.client.load.mixin;

import me.zero.client.wrapper.IKeyBinding;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(KeyBinding.class)
public class MixinKeyBinding implements IKeyBinding {

    @Shadow private boolean pressed;

    @Override
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
