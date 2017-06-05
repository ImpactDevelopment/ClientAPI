package me.zero.client.load.mixin.wrapper;

import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/20/2017 12:00 PM
 */
@Mixin(RenderManager.class)
public interface IRenderManager {

    /**
     * @return Render Pos X
     */
    @Accessor double getRenderPosX();

    /**
     * @return Render Pos Y
     */
    @Accessor double getRenderPosY();

    /**
     * @return Render Pos Z
     */
    @Accessor double getRenderPosZ();
}
