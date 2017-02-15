package me.zero.client.load.transformer.hook;

import javassist.CannotCompileException;
import javassist.CtMethod;

/**
 * Used in transformers to transform a method
 * belonging to an already-hooked class
 *
 * @see me.zero.client.load.transformer.hook.ClassHook
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public interface MethodHook {

    /**
     * @since 1.0
     *
     * @param ctMethod Method going to get transformed
     */
    void accept(CtMethod ctMethod) throws CannotCompileException;
}
