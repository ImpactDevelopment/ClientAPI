package me.zero.example.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Brady on 4/30/2017.
 */
@Mixin(Minecraft.class)
public class ExampleMixin {

    @Inject(method = "runTick", at = @At("HEAD"))
    public void runTickHook(CallbackInfo ci) {
        // System.out.println("Running game tick");
    }
}