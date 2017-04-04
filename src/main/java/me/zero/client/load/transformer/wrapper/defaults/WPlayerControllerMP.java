package me.zero.client.load.transformer.wrapper.defaults;

import me.zero.client.wrapper.IPlayerControllerMP;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static javassist.CtClass.*;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IPlayerControllerMP to PlayerControllerMP
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
@LoadTransformer
public final class WPlayerControllerMP extends ClassWrapper {

    public WPlayerControllerMP() {
        super(PlayerControllerMP, IPlayerControllerMP.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementR("isHittingBlock", booleanType, isHittingBlock);
        this.implementR("getCurBlockDamage", floatType, curBlockDamageMP);
        this.implementS("setCurBlockDamage", floatType, curBlockDamageMP);
    }
}
