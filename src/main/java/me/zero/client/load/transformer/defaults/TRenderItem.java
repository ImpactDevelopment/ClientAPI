package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.RenderItem;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.renderEffect;

/**
 * Used to hook the GlintEffect Event for Items
 *
 * @since 1.0
 *
 * Created by Brady on 2/19/2017.
 */
public class TRenderItem extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(renderEffect.createHook(method -> method.insertBefore("{ GlintEffectEvent event = new GlintEffectEvent(GlintEffectEvent.GlintTarget.ITEM); EventManager.post(event); if (event.isCancelled()) return; }")));
    }

    @Override
    public ClassReference getTargetClass() {
        return RenderItem;
    }
}
