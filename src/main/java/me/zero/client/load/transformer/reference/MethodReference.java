package me.zero.client.load.transformer.reference;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.api.util.MethodDescriptorBuilder;
import me.zero.client.load.transformer.hook.MethodHook;
import me.zero.client.load.transformer.reference.obfuscation.ObfuscationName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Type of {@code Reference} designed for Methods.
 *
 * @see me.zero.client.load.transformer.reference.ClassReference
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public final class MethodReference extends Reference {

    /**
     * {@code ClassReference} to the return type of the method
     * represented by this {@code MethodReference}
     */
    private ClassReference returnType;

    /**
     * A list of {@code ClassReferences} to represent
     * the paramaters that belong to the method represented
     * by this {@code MethodReference}
     */
    private ClassReference[] parameters;

    public MethodReference(ObfuscationName[] names, ClassReference returnType, Object... parameters) {
        super(names);
        this.returnType = returnType;

        List<ClassReference> parameterList = new ArrayList<>();
        for (Object o : parameters) {
            if (o instanceof ClassReference)
                parameterList.add((ClassReference) o);
            if (o instanceof Type)
                parameterList.add(new ClassReference((Type) o));
        }
        this.parameters = parameterList.toArray(new ClassReference[0]);
    }

    public MethodReference(ObfuscationName[] names, Type returnType, Object... parameters) {
        this(names, new ClassReference(returnType), parameters);
    }

    /**
     * @since 1.0
     *
     * @return ClassReference representation of the method return type
     */
    public ClassReference getReturnType() {
        return this.returnType;
    }

    /**
     * @since 1.0
     *
     * @return ClassReference array representation of the method parameters
     */
    public ClassReference[] getParameters() {
        return this.parameters;
    }

    /**
     * Retrieves the {@code CtMethod} representation of the Method
     * represented by this {@code MethodReference} based
     * on the parent {@code CtClass}
     *
     * @since 1.0
     *
     * @see #createHook(MethodHook)
     *
     * @param ctClass Parent Class
     * @return The CtMethod representation
     * @throws NotFoundException if the method cannot be found
     */
    private CtMethod getMethod(CtClass ctClass) throws NotFoundException {
        return this.getMethod(ctClass, new MethodDescriptorBuilder(this).build());
    }

    /**
     * Retrieves the {@code CtMethod} representation of the Method
     * represented by this {@code MethodReference} based
     * on the parent {@code CtClass}
     *
     * @since 1.0
     *
     * @see #createHook(MethodHook)
     *
     * @param ctClass Parent Class
     * @return The CtMethod representation
     * @throws NotFoundException if the method cannot be found
     */
    private CtMethod getMethod(CtClass ctClass, String description) throws NotFoundException {
        return ctClass.getMethod(this.getName(), description);
    }

    /**
     * Creates a ClassHook from the CtMethod that is
     * represented by this class. Used during transformation
     * to load all of the {@code ClassHooks} used by a {@code Transformer}
     *
     * @since 1.0
     *
     * @see me.zero.client.load.transformer.ITransformer
     *
     * @param hook The hook to the method
     * @return ClassHook to be passed to a {@code Transformer}
     */
    public ClassHook createHook(MethodHook hook) {
        return ctClass -> hook.accept(getMethod(ctClass));
    }

    /**
     * Creates a ClassHook from the CtMethod that is
     * represented by this class. Used during transformation
     * to load all of the {@code ClassHooks} used by a {@code Transformer}
     *
     * @since 1.0
     *
     * @see me.zero.client.load.transformer.ITransformer
     *
     * @param hook The hook to the method
     * @return ClassHook to be passed to a {@code Transformer}
     */
    public ClassHook createHook(MethodHook hook, String descriptor) {
        return ctClass -> hook.accept(getMethod(ctClass, descriptor));
    }
}
