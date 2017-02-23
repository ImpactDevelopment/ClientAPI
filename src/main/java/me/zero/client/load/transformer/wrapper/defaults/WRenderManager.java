package me.zero.client.load.transformer.wrapper.defaults;

import javassist.CtPrimitiveType;
import me.zero.client.api.wrapper.IRenderManager;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wrapper for IRenderManager
 *
 * @since 1.0
 *
 * Created by Brady on 2/23/2017.
 */
public class WRenderManager extends ClassWrapper {

    public WRenderManager() {
        super(RenderManager, IRenderManager.class);
    }

    @Override
    protected void loadImplementations() {
        this.implement("getRenderPosX", CtPrimitiveType.doubleType, renderPosX);
        this.implement("getRenderPosY", CtPrimitiveType.doubleType, renderPosY);
        this.implement("getRenderPosZ", CtPrimitiveType.doubleType, renderPosZ);
    }
}
