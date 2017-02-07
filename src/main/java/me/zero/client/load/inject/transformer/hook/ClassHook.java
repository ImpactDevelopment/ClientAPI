package me.zero.client.load.inject.transformer.hook;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * Used in transformers to hook classes
 *
 * @see me.zero.client.load.inject.transformer.hook.MethodHook
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public interface ClassHook {

    /**
     * @since 1.0
     *
     * @param ctClass Class being hooked
     * @throws NotFoundException
     */
    void accept(CtClass ctClass) throws NotFoundException, CannotCompileException;
}
