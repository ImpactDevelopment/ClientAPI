package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to hook into the renderItemInFirstPerson method.
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
 */
@LoadTransformer
public class TItemRenderer extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(renderItemInFirstPerson.createHook(method -> method.insertBefore("{ ItemRenderEvent event = new ItemRenderEvent(this, $2, $4, $5, $6, $7); EventManager.post(event); if (event.isCancelled()) return; }")));
    }

    @Override
    public ClassReference getTargetClass() {
        return ItemRenderer;
    }
}
