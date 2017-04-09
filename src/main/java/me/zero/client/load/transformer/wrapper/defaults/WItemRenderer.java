package me.zero.client.load.transformer.wrapper.defaults;

import javassist.CtClass;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;
import me.zero.client.wrapper.IItemRenderer;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IItemRenderer to ItemRenderer
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
 */
@LoadTransformer
public final class WItemRenderer extends ClassWrapper {

    public WItemRenderer() {
        super(ItemRenderer, IItemRenderer.class);
    }

    @Override
    protected void loadImplementations() {
        String code = "this.%s($1, $2);";
        this.implement("setupSideFirstPerson", CtClass.voidType, new CtClass[] { EnumHandSide.getCtClass(), CtClass.floatType }, String.format(code, transformSideFirstPerson.getName()));
        this.implement("setupFirstPerson", CtClass.voidType, new CtClass[] { EnumHandSide.getCtClass(), CtClass.floatType }, String.format(code, transformFirstPerson.getName()));
    }
}
