package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to hook the BlockCollisionEvent
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
 */
@LoadTransformer
public final class TBlock extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(canCollideCheck.createHook(method -> method.insertBefore("{ BlockCollisionEvent event = new BlockCollisionEvent(this); EventManager.post(event); if (event.isCancelled()) return false; }")));

        String aabb = String.format("$1.%s($2, $3)", getCollisionBoundingBox.getName());
        hooks.add(addCollisionBoxToList.createHook(method -> method.insertBefore("{ BoundingBoxEvent event = new BoundingBoxEvent(this, $3, " + aabb + "); EventManager.post(event); if (event.isCancelled()) return; }")));
    }

    @Override
    public ClassReference getTargetClass() {
        return Block;
    }
}
