package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

/**
 * Called from {@code RenderLivingBase#renderLayers(EntityLivingBase, float, float, float, float, float, float, float)}.
 * When cancelled, the layer rendering won't execute.
 *
 * @author Brady
 * @since 4/23/2017 12:00 PM
 */
public final class LayerRenderEvent extends Cancellable {

    private final EntityLivingBase entity;
    private final LayerRenderer layerRenderer;

    public LayerRenderEvent(EntityLivingBase entity, LayerRenderer layerRenderer) {
        this.entity = entity;
        this.layerRenderer = layerRenderer;
    }

    public final EntityLivingBase getEntity() {
        return this.entity;
    }

    public final LayerRenderer getLayerRenderer() {
        return this.layerRenderer;
    }
}
