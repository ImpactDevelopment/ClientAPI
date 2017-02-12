package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.LoadTransformer;
import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to patch the Camera FOV modifier
 *
 * @since 1.0
 *
 * Created by Brady on 2/11/2017.
 */
@LoadTransformer
public class TEntityRenderer extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(getFOVModifier.createHook(method -> method.insertBefore("if (Camera.isCapturing()) { return 90.0F; }")));
    }

    @Override
    public void loadImports(List<String> imports) {
        imports.add("me.zero.client.api.util.render.camera");
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { EntityRenderer };
    }
}
