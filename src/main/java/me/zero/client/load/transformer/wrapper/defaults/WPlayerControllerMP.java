package me.zero.client.load.transformer.wrapper.defaults;

import javassist.CtPrimitiveType;
import me.zero.client.api.wrapper.IPlayerControllerMP;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.PlayerControllerMP;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.curBlockDamageMP;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.isHittingBlock;

/**
 * Used to create getters and setters for PlayerControllerMP
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
public class WPlayerControllerMP extends ClassWrapper {

    public WPlayerControllerMP() {
        super(PlayerControllerMP, IPlayerControllerMP.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementR("isHittingBlock", CtPrimitiveType.booleanType, isHittingBlock);
        this.implementR("getCurBlockDamage", CtPrimitiveType.floatType, curBlockDamageMP);
        this.implementS("setCurBlockDamage", CtPrimitiveType.floatType, curBlockDamageMP);
    }
}
