package me.zero.client.api.transformer.reference;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import me.zero.client.api.transformer.hook.ClassHook;
import me.zero.client.api.util.MethodDescriptorBuilder;
import me.zero.client.api.transformer.hook.MethodHook;
import me.zero.client.api.transformer.reference.obfuscation.ObfuscationName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class MethodReference extends Reference {

    private ClassReference returnType;
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

    public ClassReference getReturnType() {
        return this.returnType;
    }

    public ClassReference[] getParameters() {
        return this.parameters;
    }

    public CtMethod getMethod(CtClass ctClass) throws NotFoundException {
        return ctClass.getMethod(this.getName(), new MethodDescriptorBuilder(this.returnType, this.parameters).build());
    }

    public ClassHook createHook(MethodHook hook) {
        return ctClass -> hook.accept(getMethod(ctClass));
    }
}
