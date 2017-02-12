package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.LoadTransformer;
import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

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
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(renderGameOverlay.createHook(method -> method.insertAfter("{ EventManager.post(new Render2DEvent($1)); }")));
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { GuiIngame };
    }
}
