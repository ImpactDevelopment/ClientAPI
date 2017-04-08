package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used for the EntityCollisionEvent
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
 */
@LoadTransformer
public final class TEntity extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(applyEntityCollision.createHook(method -> method.insertBefore("{ EntityCollisionEvent event = new EntityCollisionEvent(this, $1); EventManager.post(event); if (event.isCancelled()) return; }")));
    }

    @Override
    public ClassReference getTargetClass() {
        return Entity;
    }
}
