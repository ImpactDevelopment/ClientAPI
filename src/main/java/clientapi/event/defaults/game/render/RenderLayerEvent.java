/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.event.defaults.game.render;

import me.zero.alpine.event.Cancellable;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

/**
 * Called from {@link RenderLivingBase#renderLayers(EntityLivingBase, float, float, float, float, float, float, float)}.
 * When cancelled, the layer rendering won't execute.
 *
 * @author Brady
 * @since 4/23/2017
 */
public final class RenderLayerEvent extends Cancellable {

    private final EntityLivingBase entity;
    private final LayerRenderer layerRenderer;

    public RenderLayerEvent(EntityLivingBase entity, LayerRenderer layerRenderer) {
        this.entity = entity;
        this.layerRenderer = layerRenderer;
    }

    public final EntityLivingBase getEntity() {
        return this.entity;
    }

    public final LayerRenderer getLayerRenderer() {
        return this.layerRenderer;
    }

    @Override
    public String toString() {
        return "LayerRenderEvent{" +
                "entity=" + entity +
                ", layerRenderer=" + layerRenderer +
                '}';
    }
}
