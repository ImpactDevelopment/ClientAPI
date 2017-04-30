package me.zero.client.load.mixin;

import me.zero.client.wrapper.IRenderManager;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(RenderManager.class)
public class MixinRenderManager implements IRenderManager {

    @Shadow private double renderPosX;
    @Shadow private double renderPosY;
    @Shadow private double renderPosZ;

    @Override
    public double getRenderPosX() {
        return this.renderPosX;
    }

    @Override
    public double getRenderPosY() {
        return this.renderPosY;
    }

    @Override
    public double getRenderPosZ() {
        return this.renderPosZ;
    }
}
