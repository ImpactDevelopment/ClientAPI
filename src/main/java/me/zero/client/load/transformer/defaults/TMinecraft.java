package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Creates a hook for the Tick and Loop Events
 *
 * @since 1.0
 *
 * Created by Brady on 1/24/2017.
 */
@LoadTransformer
public final class TMinecraft extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(runTick.createHook(method -> method.insertBefore("EventManager.post(new TickEvent());")));
        hooks.add(runGameLoop.createHook(method -> method.insertBefore("EventManager.post(new LoopEvent());")));
        hooks.add(init.createHook(method -> method.insertAfter("ClientLoader.runClient();")));

        hooks.add(clickMouse.createHook(method -> method.insertBefore("EventManager.post(new ClickEvent(ClickEvent.MouseButton.LEFT));")));
        hooks.add(rightClickMouse.createHook(method -> method.insertBefore("EventManager.post(new ClickEvent(ClickEvent.MouseButton.RIGHT));")));
        hooks.add(middleClickMouse.createHook(method -> method.insertBefore("EventManager.post(new ClickEvent(ClickEvent.MouseButton.MIDDLE));")));
        hooks.add(loadWorld.createHook(method -> method.insertAfter("if ($1 != null) { EventManager.post(new WorldLoadEvent($1)); }")));
    }

    @Override
    public void loadImports(Collection<String> imports) {
        imports.add("me.zero.client.load.discover");
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { Minecraft };
    }
}
