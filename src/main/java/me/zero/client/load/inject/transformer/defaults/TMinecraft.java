package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Creates a hook for the Tick and Loop Events
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
        hooks.add(startGame.createHook(method -> method.insertAfter("ClientLoader.initGameLoader();")));

        hooks.add(clickMouse.createHook(method -> method.insertBefore("EventManager.post(new ClickEvent(ClickEvent.MouseButton.LEFT));")));
        hooks.add(rightClickMouse.createHook(method -> method.insertBefore("EventManager.post(new ClickEvent(ClickEvent.MouseButton.RIGHT));")));
        hooks.add(middleClickMouse.createHook(method -> method.insertBefore("EventManager.post(new ClickEvent(ClickEvent.MouseButton.MIDDLE));")));
        hooks.add(loadWorld.createHook(method -> method.insertAfter("if ($1 != null) { EventManager.post(new WorldLoadEvent($1); }")));
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
