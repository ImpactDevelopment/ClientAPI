package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Creates a hook for the Render 2D Event
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
@LoadTransformer
public final class TGuiIngame extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(renderGameOverlay.createHook(method -> method.insertAfter("{ EventManager.post(new Render2DEvent($1)); }")));
    }

    @Override
    public ClassReference getTargetClass() {
        return GuiIngame;
    }
}
