package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.EntityPlayerSP;
import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.onUpdate;

/**
 * Used to hook the UpdateEvent
 *
 * @since 1.0
 *
 * Created by Brady on 2/8/2017.
 */
public class TEntityPlayerSP extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(onUpdate.createHook(method -> method.insertBefore("EventManager.post(new UpdateEvent());")));
    }

    @Override
    public void loadImports(List<String> imports) {}

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { EntityPlayerSP };
    }
}
