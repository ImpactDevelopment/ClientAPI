package me.zero.client.load.transformer.reference;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.obfuscation.ObfuscationName;

import java.lang.reflect.Type;

/**
 * Type of {@code Reference} designed for Classes.
 *
 * @see me.zero.client.load.transformer.reference.MethodReference
 * @see me.zero.client.load.transformer.reference.FieldReference
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public final class ClassReference extends Reference {

    public ClassReference(Type type) {
        this(from(type));
    }

    public ClassReference(ObfuscationName[] names) {
        super(names);
    }

    /**
     * @since 1.0
     *
     * @return The Descriptor of this Class
     */
    public String getDescriptor() {
        return Descriptor.of(this.getName());
    }

    /**
     * @since 1.0
     *
     * @return The CtClass representation of this Class
     */
    public CtClass getCtClass() throws NotFoundException {
        return ClassPool.getDefault().get(this.getName());
    }
}
