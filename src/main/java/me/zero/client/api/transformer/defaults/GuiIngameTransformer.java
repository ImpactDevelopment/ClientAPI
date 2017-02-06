package me.zero.client.api.transformer.defaults;

import me.zero.client.api.transformer.Transformer;
import me.zero.client.api.transformer.hook.ClassHook;
import me.zero.client.api.transformer.reference.ClassReference;
import me.zero.client.api.transformer.reference.obfuscation.MCMappings;

import java.util.List;

/**
 * Creates a hook for the Render 2D Event
 *
 * @see me.zero.client.api.event.defaults.Render2DEvent
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
public class GuiIngameTransformer extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(MCMappings.renderGameOverlay.createHook(method -> method.insertAfter("{ EventManager.post(new Render2DEvent($1)); }")));
    }

    @Override
    public void loadImports(List<String> imports) {}

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { MCMappings.GuiIngame };
    }
}
