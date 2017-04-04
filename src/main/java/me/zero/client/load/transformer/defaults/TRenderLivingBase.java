package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to hook EntityRenderEvent at the beginning and end of
 * RenderLivingBase#doRender(Entity, double, double, double, float, float)
 *
 * @since 1.0
 *
 * Created by Brady on 3/2/2017.
 */
@LoadTransformer
public final class TRenderLivingBase extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        String code = "EventManager.post(new EntityRenderEvent(EventState.%s, this, $1));";
        hooks.add(doRender.createHook(method -> {
            method.insertBefore(String.format(code, "PRE"));
            method.insertAfter(String.format(code, "POST"));
        }));
    }

    @Override
    public ClassReference getTargetClass() {
        return RenderLivingBase;
    }
}
