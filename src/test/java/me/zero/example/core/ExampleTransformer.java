package me.zero.example.core;

import me.zero.client.load.inject.transformer.LoadTransformer;
import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.hook.ClassHook;
import me.zero.client.load.inject.transformer.reference.ClassReference;
import me.zero.client.load.inject.transformer.reference.obfuscation.MCMappings;

import java.util.List;

/**
 * Created by Brady on 1/22/2017.
 */
@LoadTransformer
public class ExampleTransformer extends Transformer implements MCMappings {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        // Create a new hook from "runTick"
        hooks.add(runTick.createHook(ctMethod -> {
            // Insert our code at the top of the method
            // Commented out b/c console spam
            // ctMethod.insertBefore("System.out.println(\"Running Game Tick\");");
        }));
    }

    @Override
    public void loadImports(List<String> imports) {
        // No Imports Required
    }

    @Override
    public ClassReference[] getTargetClasses() {
        // We're targeting the "runTick" method inside of "Minecraft"
        return new ClassReference[] { Minecraft };
    }
}
