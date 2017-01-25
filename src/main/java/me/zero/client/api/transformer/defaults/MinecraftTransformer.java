package me.zero.client.api.transformer.defaults;

import me.zero.client.api.transformer.Transformer;
import me.zero.client.api.transformer.hook.ClassHook;
import me.zero.client.api.transformer.reference.ClassReference;
import me.zero.client.api.transformer.reference.obfuscation.MCMappings;

import java.util.List;

/**
 * Creates a hook for the Tick Event
 *
 * @see me.zero.client.api.event.defaults.TickEvent
 *
 * @since 1.0
 *
 * Created by Brady on 1/24/2017.
 */
public class MinecraftTransformer extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(MCMappings.runTick.createHook(method -> method.insertBefore("EventManager.post(new TickEvent());")));
        hooks.add(MCMappings.runGameLoop.createHook(method -> method.insertBefore("EventManager.post(new LoopEvent());")));
    }

    @Override
    public void loadImports(List<String> imports) {}

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { MCMappings.Minecraft };
    }
}
