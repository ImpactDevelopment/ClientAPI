package me.zero.client.load.transformer.wrapper.defaults;

import javassist.CtPrimitiveType;
import me.zero.client.api.wrapper.IRenderManager;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IRenderManager to RenderManager
 *
 * @since 1.0
 *
 * Created by Brady on 2/23/2017.
 */
@LoadTransformer
public class WRenderManager extends ClassWrapper {

    public WRenderManager() {
        super(RenderManager, IRenderManager.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementR("getRenderPosX", CtPrimitiveType.doubleType, renderPosX);
        this.implementR("getRenderPosY", CtPrimitiveType.doubleType, renderPosY);
        this.implementR("getRenderPosZ", CtPrimitiveType.doubleType, renderPosZ);
    }
}
