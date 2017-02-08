package me.zero.client.load.inject.transformer.defaults;

import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;

import java.util.List;

import static me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings.*;

/**
 * Created by Brady on 2/8/2017.
 */
public class TMain extends Transformer {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        hooks.add(main.createHook(method -> method.insertBefore("ClientLoader.getGameLoader($1);"), "([Ljava/lang/String;)V"));
    }

    @Override
    public void loadImports(List<String> imports) {
        imports.add("me.zero.client.load");
    }

    @Override
    public ClassReference[] getTargetClasses() {
        return new ClassReference[] { Main };
    }
}
