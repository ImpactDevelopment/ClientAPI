package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to patch the Camera FOV modifier
 *
 * @since 1.0
 *
 * Created by Brady on 2/11/2017.
 */
@LoadTransformer
public final class TEntityRenderer extends Transformer {

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
