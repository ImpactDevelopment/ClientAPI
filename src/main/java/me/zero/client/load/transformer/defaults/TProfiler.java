package me.zero.client.load.transformer.defaults;

import me.zero.client.api.util.render.camera.Camera;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Really dumb way of hookin the Render3DEvent.
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
@LoadTransformer
public final class TProfiler extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(startSection.createHook(method -> method.insertBefore("if ($1 != null && $1.equalsIgnoreCase(\"hand\") && !Camera.isCapturing()) { EventManager.post(new Render3DEvent()); }")));
    }

    @Override
    public void loadImports(Collection<String> imports) {
        imports.add(Camera.class.getName());
    }

    @Override
    public ClassReference getTargetClass() {
        return Profiler;
    }
}
