package me.zero.client.api.transformer.hook;

import javassist.CtMethod;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public interface MethodHook {

    void accept(CtMethod ctMethod);
}
