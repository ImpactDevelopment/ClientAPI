package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.hook.MethodHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Created by Brady on 3/30/2017.
 */
@LoadTransformer
public class TFontRenderer extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        MethodHook hook = method -> method.insertBefore("{ EventManager.post(new TextEvent($1)); }");
        hooks.add(renderString.createHook(hook));
        hooks.add(getStringWidth.createHook(hook));
    }

    @Override
    public ClassReference getTargetClass() {
        return FontRenderer;
    }
}
