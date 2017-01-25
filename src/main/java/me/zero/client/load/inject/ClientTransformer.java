package me.zero.client.load.inject;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.manage.Loadable;
import me.zero.client.api.transformer.ITransformer;
import me.zero.client.api.transformer.Transformer;
import me.zero.client.api.transformer.reference.ClassReference;
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
public class ClientTransformer implements IClassTransformer, Loadable {

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
        Reflections reflections = new Reflections("me.zero.client.api.transformer.defaults");
        for (Class<? extends Transformer> transformer : reflections.getSubTypesOf(Transformer.class)) {
            try {
                this.transformers.add(transformer.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                Logger.instance.logf(Level.WARNING, Messages.TRANSFORM_INSTANTIATION, transformer.getCanonicalName());
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

                ClassPool pool = ClassPool.getDefault();
                CtClass ctClass = pool.get(className);

                ctClass.stopPruning(true);
                ctClass.defrost();

                valid.forEach(transformer -> {
                    Logger.instance.logf(Level.INFO, Messages.TRANSFORM, className, transformer.getClass().getCanonicalName());
                    transformer.transform(ctClass);
                });

                return ctClass.toBytecode();

            } catch (CannotCompileException exception) {
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
