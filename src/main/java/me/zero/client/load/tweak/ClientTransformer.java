package me.zero.client.load.tweak;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import me.zero.client.api.util.interfaces.Loadable;
import me.zero.client.load.discover.ClientLoader;
import me.zero.client.load.transformer.ITransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.reference.ClassReference;
import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
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
        this.transformers.addAll(ClientLoader.getTransformers());

        // Load Default Transformers
        new Reflections("me.zero.client.load.inject.transformer.defaults")
                .getSubTypesOf(Transformer.class).forEach(transformer -> {
            try {
                this.transformers.add(transformer.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_INSTANTIATION, transformer);
            }
        });
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        String className = Descriptor.toJavaName(transformedName);
        List<ITransformer> valid = getTransformers(className);
        if (!valid.isEmpty()) {
            try {
                return this.transform(ClassPool.getDefault().get(className), valid);
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

    /**
     * Sets up the ClassPool for the next transformer
     *
     * @since 1.0
     *
     * @param transformer The transformer
     */
    private void setup(ITransformer transformer) {
        List<String> imports = new ArrayList<>();
        transformer.loadImports(imports);
        this.loadDefaultImports(imports);
        imports.forEach(ClassPool.getDefault()::importPackage);
    }

    /**
     * Gets the transformers that are targeting
     * the specified class name.
     *
     * @since 1.0
     *
     * @param className The class name
     */
    private List<ITransformer> getTransformers(String className) {
        return transformers.stream().filter(transformer -> {
            for (ClassReference target : transformer.getTargetClasses())
                if (target.getName().equalsIgnoreCase(className))
                    return true;
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * Transforms the specified class with
     * the specified list of transformers.
     *
     * @since 1.0
     *
     * @param ctClass The CtClass being transformed
     * @param transformers The transformers being used
     * @return The transformed class, represented as bytecode
     */
    private byte[] transform(CtClass ctClass, List<ITransformer> transformers) throws CannotCompileException, IOException {
        transformers.forEach(transformer -> {
            this.setup(transformer);
            Logger.instance.logf(Level.INFO, Messages.TRANSFORM, ctClass.getName(), transformer.getClass().getCanonicalName());
            transformer.transform(ctClass);
        });
        return ctClass.toBytecode();
    }


    /**
     * Loads any imports that're going to be used for
     * a large majority of the transformers.
     *
     * @since 1.0
     *
     * @param imports The list being appended to
     */
    private void loadDefaultImports(List<String> imports) {
        imports.add("me.zero.client.api.event");
        imports.add("me.zero.client.api.event.type");
        imports.add("me.zero.client.api.event.defaults");
    }
}
