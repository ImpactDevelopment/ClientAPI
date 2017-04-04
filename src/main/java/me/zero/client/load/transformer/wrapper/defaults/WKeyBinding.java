package me.zero.client.load.transformer.wrapper.defaults;

import me.zero.client.wrapper.IKeyBinding;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static javassist.CtClass.*;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IKeyBinding to KeyBinding
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
@LoadTransformer
public final class WKeyBinding extends ClassWrapper {

    public WKeyBinding() {
        super(KeyBinding, IKeyBinding.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementS("setPressed", booleanType, pressed);
    }
}
