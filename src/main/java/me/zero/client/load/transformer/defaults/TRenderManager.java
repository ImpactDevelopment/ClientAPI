package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;
import me.zero.client.load.transformer.wrapper.defaults.WRenderManager;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to add the RenderManager Wrapper
 *
 * @since 1.0
 *
 * Created by Brady on 2/20/2017.
 */
@LoadTransformer
public class TRenderManager extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(new WRenderManager().createClassHook());
    }

    @Override
    public ClassReference getTargetClass() {
        return RenderManager;
    }
}
