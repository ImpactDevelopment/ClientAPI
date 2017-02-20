package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.LayerArmorBase;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.renderEnchantedGlint;

/**
 * Used to hook the GlintEffect Event for Armor Layers
 *
 * @since 1.0
 *
 * Created by Brady on 2/19/2017.
 */
@LoadTransformer
public class TLayerArmorBase extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(renderEnchantedGlint.createHook(method -> method.insertBefore("{ GlintEffectEvent event = new GlintEffectEvent(GlintEffectEvent.GlintTarget.ARMOR); EventManager.post(event); if (event.isCancelled()) return; }")));
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { LayerArmorBase };
    }
}
