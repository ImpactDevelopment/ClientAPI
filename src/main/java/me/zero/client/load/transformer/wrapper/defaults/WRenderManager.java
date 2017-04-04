package me.zero.client.load.transformer.wrapper.defaults;

import me.zero.client.wrapper.IRenderManager;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static javassist.CtClass.doubleType;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IRenderManager to RenderManager
 *
 * @since 1.0
 *
 * Created by Brady on 2/23/2017.
 */
@LoadTransformer
public final class WRenderManager extends ClassWrapper {

    public WRenderManager() {
        super(RenderManager, IRenderManager.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementR("getRenderPosX", doubleType, renderPosX);
        this.implementR("getRenderPosY", doubleType, renderPosY);
        this.implementR("getRenderPosZ", doubleType, renderPosZ);
    }
}
