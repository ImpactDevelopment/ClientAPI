package me.zero.client.api.transformer;

import javassist.CtClass;
import me.zero.client.api.transformer.hook.ClassHook;
import me.zero.client.api.transformer.reference.ClassReference;

import java.util.List;

/**
 * The Base for Transformers
 *
 * @see me.zero.client.api.transformer.Transformer
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public interface ITransformer {

    /**
     * Used to append hooks to a list
     *
     * @param hooks The list getting appended to
     */
    void loadHooks(List<ClassHook> hooks);

    /**
     * Used to append imports to a list
     *
     * @param imports The list getting appended to
     */
    void loadImports(List<String> imports);

    /**
     * Transforms the specified CtClass
     *
     * @param ctClass Class being Transformed
     */
    void transform(CtClass ctClass);

    /**
     * @return Classes that are being targeted for Transformation
     */
    ClassReference[] getTargetClasses();
}
