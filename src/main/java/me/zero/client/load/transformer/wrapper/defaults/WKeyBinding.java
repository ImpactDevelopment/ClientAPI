package me.zero.client.load.transformer.wrapper.defaults;

import javassist.CtPrimitiveType;
import me.zero.client.api.wrapper.IKeyBinding;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps IKeyBinding to KeyBinding
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
@LoadTransformer
public class WKeyBinding extends ClassWrapper {

    public WKeyBinding() {
        super(KeyBinding, IKeyBinding.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementS("setPressed", CtPrimitiveType.booleanType, pressed);
    }
}
