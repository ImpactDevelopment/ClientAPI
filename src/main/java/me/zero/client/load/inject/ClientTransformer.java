package me.zero.client.load.inject;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.util.interfaces.Loadable;
import me.zero.client.load.inject.transformer.ITransformer;
import me.zero.client.load.inject.transformer.LoadTransformer;
import me.zero.client.load.inject.transformer.Transformer;
import me.zero.client.load.inject.transformer.defaults.*;
import me.zero.client.load.inject.transformer.reference.ClassReference;
import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.ClientAPI;
import net.minecraft.launchwrapper.IClassTransformer;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Used to Transform the game classes in order
 * to inject events and make code modifications
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public final class ClientTransformer implements IClassTransformer, Loadable {

    /**
     * List of all of the Transformers
     */
    private List<ITransformer> transformers = new ArrayList<>();

    public ClientTransformer() {
        this.load();
    }

    @Override
    public void load() {
        ClassPool.getDefault();

        // Load Client Transformers
        this.transformers.addAll(ClientAPI.getAPI().getLoader().getTransformers());

        // Load Default Transformers
        Reflections reflections = new Reflections("me.zero.client.load.inject.transformer.defaults");
        for (Class<? extends Transformer> transformer : reflections.getSubTypesOf(Transformer.class)) {
            if (!transformer.isAnnotationPresent(LoadTransformer.class)) continue;
            try {
                this.transformers.add(transformer.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_INSTANTIATION, transformer);
            }
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        final String className = transformedName.replaceAll("/", ".");

        List<ITransformer> valid = transformers.stream().filter(transformer -> {
            for (ClassReference target : transformer.getTargetClasses()) {
                if (target.getName().equalsIgnoreCase(className))
                    return true;
            }
            return false;
        }).collect(Collectors.toList());

        if (!valid.isEmpty()) {
            try {
                CtClass ctClass = ClassPool.getDefault().get(className);

                valid.forEach(transformer -> {
                    Logger.instance.logf(Level.INFO, Messages.TRANSFORM, className, transformer.getClass().getCanonicalName());
                    transformer.transform(ctClass);
                });

                return ctClass.toBytecode();

            } catch (CannotCompileException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_CANNOT_COMPILE, className);
            } catch (IOException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_UNEXPECTED_IOEXCEPTION, className);
            } catch (NotFoundException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_CLASS_NOT_FOUND, className);
            }
        }

        return basicClass;
    }
}
