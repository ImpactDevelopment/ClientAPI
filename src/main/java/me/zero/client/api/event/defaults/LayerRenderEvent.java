package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import net.minecraft.entity.EntityLivingBase;

/**
 * Called from {@code RenderLivingBase#renderLayers(EntityLivingBase, float, float, float, float, float, float, float)}.
 * When cancelled, the layer rendering won't execute.
 *
 * @since 1.0
 *
 * Created by Brady on 4/23/2017.
 */
public final class LayerRenderEvent extends Cancellable {

    private final EntityLivingBase entity;

    public LayerRenderEvent(EntityLivingBase entity) {
        this.entity = entity;
    }

    public final EntityLivingBase getEntity() {
        return this.entity;
    }
}
