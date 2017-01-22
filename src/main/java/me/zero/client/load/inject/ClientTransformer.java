package me.zero.client.load.inject;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.manage.Loadable;
import me.zero.client.api.transformer.ITransformer;
import me.zero.client.api.transformer.reference.ClassReference;
import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import net.minecraft.launchwrapper.IClassTransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class ClientTransformer implements IClassTransformer, Loadable {

    private List<ITransformer> transformers = new ArrayList<>();

    public ClientTransformer() {
        this.load();
    }

    @Override
    public void load() {
        // Load Default and Client Transformers
        ClassPool.getDefault();
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

            } catch (CannotCompileException | IOException | NotFoundException exception) {

                if (exception instanceof CannotCompileException)
                    Logger.instance.logf(Level.SEVERE, Messages.TRANSFOM_CANNOT_COMPILE, className);

                if (exception instanceof IOException)
                    Logger.instance.logf(Level.SEVERE, Messages.TRANSFOM_UNEXPECTED_IOEXCEPTION, className);

                if (exception instanceof NotFoundException)
                    Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_CLASS_NOT_FOUND, className);
            }
        }

        return basicClass;
    }
}
