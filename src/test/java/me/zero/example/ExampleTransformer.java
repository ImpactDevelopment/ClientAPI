package me.zero.example;

import me.zero.client.api.transformer.Transformer;
import me.zero.client.api.transformer.hook.ClassHook;
import me.zero.client.api.transformer.reference.ClassReference;
import me.zero.client.api.transformer.reference.obfuscation.MCMappings;

import java.util.List;

/**
 * Created by Brady on 1/22/2017.
 */
public class ExampleTransformer extends Transformer implements MCMappings {

    @Override
    public void loadHooks(List<ClassHook> hooks) {
        // Create a new hook from "runTick"
        hooks.add(runTick.createHook(ctMethod -> {
            // Insert our code at the top of the method
            ctMethod.insertBefore("System.out.println(\"Running Game Tick\");");
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
