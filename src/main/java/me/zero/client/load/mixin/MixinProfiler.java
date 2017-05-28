package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.ProfilerEvent;
import net.minecraft.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Profiler.class)
public class MixinProfiler {

    @Inject(method = "startSection", at = @At("HEAD"))
    public void startSection(String name, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new ProfilerEvent(name));
    }
}
