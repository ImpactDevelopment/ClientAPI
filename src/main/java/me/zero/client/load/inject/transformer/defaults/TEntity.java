package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Hooks the MoveEvent
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public class TEntity extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(moveEntity.createHook(method -> method.insertBefore("{ if (this instanceof " + EntityPlayerSP.getName() + ") { MoveEvent event = new MoveEvent($1, $2, $3, $4); EventManager.post(event); $2 = event.getX(); $3 = event.getY(); $4 = event.getZ(); }}")));
    }

    @Override
    public void loadImports(List<String> imports) {}

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { Entity };
    }
}
