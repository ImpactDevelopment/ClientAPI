package me.zero.client.api.transformer.hook;

import javassist.CtClass;
import javassist.NotFoundException;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public interface ClassHook {

    void accept(CtClass ctClass) throws NotFoundException;
}
