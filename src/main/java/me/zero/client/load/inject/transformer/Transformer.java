package me.zero.client.load.inject.transformer;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;
import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.inject.transformer.hook.ClassHook;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ITransformer
 *
 * @see me.zero.client.load.inject.transformer.ITransformer
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
        List<ClassHook> hooks = new ArrayList<>();
        this.loadHooks(hooks);
        hooks.forEach(hook -> {
            try {
                hook.accept(ctClass);
            } catch (NotFoundException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_METHOD_NOT_FOUND);
            } catch (CannotCompileException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_CANNOT_COMPILE, ctClass.getName());
            }
        });
    }

    /**
     * Here, we implement the load imports method from
     * ITransformer so that any classes extending this
     * are not required to have any extra imports.
     *
     * Only here to clean things up.
     */
    @Override
    public void loadImports(List<String> imports) {}
}
