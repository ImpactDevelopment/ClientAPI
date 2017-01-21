package me.zero.client.api.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.transformer.hook.ClassHook;
import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ITransformer
 *
 * @see me.zero.client.api.transformer.ITransformer
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public abstract class Transformer implements ITransformer {

    /**
     * Implementation of {@code #transform(CtClass)}
     *
     * @see ITransformer#transform(CtClass)
     *
     * @param ctClass Class being Transformed
     */
    @Override
    public final void transform(CtClass ctClass) {
        List<String> imports = new ArrayList<>();
        this.loadImports(imports);
        imports.forEach(ClassPool.getDefault()::importPackage);

        List<ClassHook> hooks = new ArrayList<>();
        this.loadHooks(hooks);
        hooks.forEach(hook -> {
            try {
                hook.accept(ctClass);
            } catch (NotFoundException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_METHOD_NOT_FOUND);
            }
        });
    }
}
