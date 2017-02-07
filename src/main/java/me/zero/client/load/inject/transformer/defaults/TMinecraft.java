package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Creates a hook for the Tick and Loop Events
 *
 * @see me.zero.client.api.event.defaults.TickEvent
 * @see me.zero.client.api.event.defaults.LoopEvent
 *
 * @since 1.0
 *
 * Created by Brady on 1/24/2017.
 */
public final class TMinecraft extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(runTick.createHook(method -> method.insertBefore("EventManager.post(new TickEvent());")));
        hooks.add(runGameLoop.createHook(method -> method.insertBefore("EventManager.post(new LoopEvent());")));
        hooks.add(startGame.createHook(method -> method.insertAfter("ClientAPI.getAPI().getLoader().runClientGameInit();")));
    }

    @Override
    public void loadImports(List<String> imports) {
        imports.add("me.zero.client.load");
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { Minecraft };
    }
}
