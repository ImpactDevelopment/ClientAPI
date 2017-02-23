package me.zero.client.load.transformer.wrapper;

import com.google.common.collect.Sets;
import javassist.*;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;
import me.zero.client.load.transformer.reference.FieldReference;

import java.util.Set;

import static me.zero.client.api.util.Messages.TRANSFORM_WRAPPER_ADD_METHOD;
import static me.zero.client.api.util.Messages.TRANSFORM_WRAPPER_COMPILE_METHOD;

/**
 * Basic class wrapper, used to attach interfaces.
 *
 * @since 1.0
 *
 * Created by Brady on 2/23/2017.
 */
public abstract class ClassWrapper {

    private Set<CtMethod> methods = Sets.newHashSet();
    private CtClass target;
    private Class<?> wrapper;

    public ClassWrapper(ClassReference target, Class<?> wrapper) {
        this.wrapper = wrapper;
        try {
            this.target = target.getCtClass();
        } catch (NotFoundException e) {
            throw new UnexpectedOutcomeException("NotFoundException when getting CtClass from ClassReference!");
        }
        this.loadImplementations();
    }

    protected abstract void loadImplementations();

    protected void implement(String methodName, CtClass returnType, FieldReference reference) {
        this.implement(methodName, returnType, reference.createReturn());
    }

    protected void implement(String methodName, CtClass returnType, String src) {
        this.implement(methodName, returnType, new CtClass[0], src);
    }

    protected void implement(String methodName, CtClass returnType, CtClass[] parameters, String src) {
        this.implement(methodName, returnType, parameters, new CtClass[0], src);
    }

    protected void implement(String methodName, CtClass returnType, CtClass[] parameters, CtClass[] exceptions, String src) {
        try {
            this.implement(CtNewMethod.make(returnType, methodName, parameters, exceptions, src, target));
        } catch (CannotCompileException e) {
            Logger.instance.logf(Level.SEVERE, TRANSFORM_WRAPPER_COMPILE_METHOD, methodName);
        }
    }

    protected void implement(CtMethod method) {
        if (!methods.contains(method))
            methods.add(method);
    }

    public boolean isImplComplete() {
        // Basic check, change later
        return wrapper.getClass().getDeclaredMethods().length == methods.size();
    }

    public ClassHook createClassHook() {
        return ctClass -> {
            ctClass.addInterface(target);
            methods.forEach(method -> {
                try {
                    ctClass.addMethod(method);
                } catch (CannotCompileException e) {
                    Logger.instance.logf(Level.SEVERE, TRANSFORM_WRAPPER_ADD_METHOD, method.getName());
                }
            });
        };
    }
}
