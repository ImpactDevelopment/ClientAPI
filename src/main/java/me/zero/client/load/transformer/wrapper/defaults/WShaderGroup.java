package me.zero.client.load.transformer.wrapper.defaults;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;
import me.zero.client.wrapper.IShaderGroup;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IShaderGroup to ShaderGroup
 *
 * @since 1.0
 *
 * Created by Brady on 4/4/2017.
 */
@LoadTransformer
public class WShaderGroup extends ClassWrapper {

    private static CtClass List;

    static {
        try {
            List = ClassPool.getDefault().get("java.util.List");
        } catch (NotFoundException e) {
            throw new UnexpectedOutcomeException("Unable to find List class");
        }
    }

    public WShaderGroup() {
        super(ShaderGroup, IShaderGroup.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementR("getShaders", List, listShaders);
        this.implementR("getFBOs", List, listFramebuffers);
    }
}
