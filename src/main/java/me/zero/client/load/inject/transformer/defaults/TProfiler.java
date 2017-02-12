package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Created by Brady on 2/9/2017.
 */
public final class TProfiler extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(startSection.createHook(method -> method.insertBefore("if ($1 != null && $1.equalsIgnoreCase(\"hand\")) { EventManager.post(new Render3DEvent()); }")));
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { Profiler };
    }
}
